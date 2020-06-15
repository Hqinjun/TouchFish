package com.ruoyi.book.common.filter;


import com.ruoyi.book.common.model.UserDetails;
import com.ruoyi.book.common.utils.JwtTokenUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
/**
 * @author Hqinjun
 * @date 2020/6/15 1:39
 */

public class JwtFilter extends GenericFilterBean {

    protected JwtTokenUtil jwtTokenUtil;

    public JwtFilter(JwtTokenUtil jwtTokenUtil){
        this.jwtTokenUtil = jwtTokenUtil;
    }



    @Override
    public void doFilter(final ServletRequest req,
                         final ServletResponse res,
                         final FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;

        //客户端将token封装在请求头中，格式为（Bearer后加空格）：Authorization：Bearer +token
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ServletException("Missing or invalid Authorization header.");
        }

        //去除Bearer 后部分
        final String token = authHeader.substring(7);

        try {
            //解密token，拿到里面的对象claims
            final UserDetails userDetails = jwtTokenUtil.getUserDetailsFromToken(token);
            //将对象传递给下一个请求
            request.setAttribute("userInfo", userDetails);
            request.setAttribute("token",token);
        }
        catch (final JwtException e) {
            throw new ServletException("Invalid token.");
        }

        chain.doFilter(req, res);
    }

}
