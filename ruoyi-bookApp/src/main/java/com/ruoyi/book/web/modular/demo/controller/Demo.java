package com.ruoyi.book.web.modular.demo.controller;

import com.ruoyi.common.utils.MessageUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hqinjun
 * @date 2020/6/15 0:09
 */
@Slf4j
@RestController
@Api(value = "demo", tags = "demo")
public class Demo {
    /**
     * demo
     *
     * @return hello world
     */
    @GetMapping("/hello")
    public String getHello() {
//        log.info(MessageUtils.message("user.password.delete"));
        log.info("1222");
        return "Hello World!";
    }

}
