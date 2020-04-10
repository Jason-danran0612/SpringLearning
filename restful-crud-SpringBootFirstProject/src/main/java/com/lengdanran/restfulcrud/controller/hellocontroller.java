package com.lengdanran.restfulcrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Classname hellocontroller
 * @Description TODO
 * @Date 2020/4/9 9:45
 * @Created by ASUS
 */
@Controller
public class hellocontroller {
    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return "hello world!";
    }
}
