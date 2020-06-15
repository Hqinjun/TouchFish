package com.ruoyi.book.common.utils;

import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.book.common.model.UserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 11797
 */
@Component
@Slf4j
public class JwtTokenUtil {

    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private int expiration;

    /**
     * SecretKey 根据 SECRET 的编码方式解码后得到：
     * Base64 编码：SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretString));
     * Base64URL 编码：SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretString));
     * 未编码：SecretKey key = Keys.hmacShaKeyFor(secretString.getBytes(StandardCharsets.UTF_8));
     * */
    private SecretKey getSecretKey() {
        byte[] encodeKey = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(encodeKey);
    }


    /**
     * 根据负责生成JWT的token
     */
    private String generateToken(Map<String, Object> claims) {

        SecretKey secretKey = getSecretKey();

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(secretKey)
                .compact();
    }

    /**
     * 从token中获取JWT中的负载信息
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        SecretKey secretKey = getSecretKey();
        try {
            claims = Jwts.parserBuilder()
                    // 解析 JWT 的服务器与创建 JWT 的服务器的时钟不一定完全同步，此设置允许两台服务器最多有 3 分钟的时差
                    .setAllowedClockSkewSeconds(180L)
                    .setSigningKey(secretKey)
                    .build().parseClaimsJws(token).getBody();
        } catch (Exception e) {
            log.info("JWT格式验证失败:{}",token);
        }
        return claims;
    }

    /**
     * 生成token的过期时间
     */
    private Date generateExpirationDate() {
        Date date = new Date();
        date = DateUtil.offsetSecond(date,expiration);
        return date;
    }

    /**
     * 从token中获取用户信息
     */
    public UserDetails getUserDetailsFromToken(String token) {
        if(isTokenExpired(token)){
            return null;
        }
        UserDetails userDetail;
        try {
            Claims claims = getClaimsFromToken(token);
             userDetail = new ObjectMapper().readValue(claims.getSubject(),UserDetails.class);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            userDetail = null;
        }
        return userDetail;
    }


    /**
     * 判断token是否已经失效
     */
    private boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * 从token中获取过期时间
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * 根据用户信息生成token
     */
    @SneakyThrows
    public String  generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>(2);
        claims.put(CLAIM_KEY_USERNAME, new ObjectMapper().writeValueAsString(userDetails));
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * 判断token是否可以被刷新
     */
    public boolean canRefresh(String token) {
        return !isTokenExpired(token);
    }

    /**
     * 刷新token
     */
    public String refreshToken(String token) {
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }


}
