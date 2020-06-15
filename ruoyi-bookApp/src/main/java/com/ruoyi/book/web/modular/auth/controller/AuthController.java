package com.ruoyi.book.web.modular.auth.controller;


import com.ruoyi.book.common.model.BookResult;
import com.ruoyi.book.common.model.UserDetails;
import com.ruoyi.book.common.utils.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName 登陆授权
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/6/27
 **/
@Slf4j
@RestController
@Api(value = "认证授权模块", tags = "认证授权模块", description = "认证授权模块")
public class AuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

//    /**
//     * 获取当前登录用户信息
//     *
//     * @return 用户信息
//     */
//    @GetMapping("/book/user")
//    @ApiOperation(value = "获取用户信息",notes = "获取用户信息")
//    public UserDetails user(ServletRequest request) {
//        return (UserDetails)request.getAttribute("userInfo");
//    }


    /**
     *获取token
     *
     * @return token字符串
     */
    @GetMapping("/oauth/token")
    @ApiOperation(value = "获取token",notes = "获取token")
    public BookResult loginReturnToken() {
        try{
            UserDetails userDetails = new UserDetails();
            userDetails.setId("1");
            userDetails.setName("2");
            userDetails.setPhone("3");
            String token = jwtTokenUtil.generateToken(userDetails);
            HashMap<String,String> map = new HashMap<>();
            map.put("token",token);
            return BookResult.success(map);
        } catch (Exception e) {
            log.error(e.getMessage());
            return BookResult.error(4000,e.getMessage());
        }
    }


    /**
     * 刷新token
     */
    @PostMapping("/refreshToken")
    @ApiOperation(value = "刷新token",notes = "刷新token")
    public BookResult refreshToken(HttpServletRequest request) {
        String token = (String) request.getAttribute("token");
        if (jwtTokenUtil.canRefresh(token)) {
            token = jwtTokenUtil.refreshToken(token);
            Map<String, Object> data = new HashMap<>(2);
            data.put("token", token);
            UserDetails userDetail = jwtTokenUtil.getUserDetailsFromToken(token);
            data.put("id", userDetail.getId());
            data.put("name", userDetail.getName());
            data.put("phone", userDetail.getPhone());
            return BookResult.success(data);
        } else {
            return BookResult.serviceFail("该用户不存在！");
        }

    }
}