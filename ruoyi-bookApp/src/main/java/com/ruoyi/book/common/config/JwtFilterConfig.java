package com.ruoyi.book.common.config;

import com.ruoyi.book.common.filter.JwtFilter;
import com.ruoyi.book.common.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Hqinjun
 * @date 2020/6/15 1:38
 */
@Configuration
public class JwtFilterConfig {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    //过滤器
    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter(jwtTokenUtil));
        registrationBean.addUrlPatterns("/book/*");
        return registrationBean;
    }

}
