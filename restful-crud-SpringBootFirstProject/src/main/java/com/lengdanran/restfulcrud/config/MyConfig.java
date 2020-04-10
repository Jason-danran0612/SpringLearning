package com.lengdanran.restfulcrud.config;

import com.lengdanran.restfulcrud.component.LoginHandlerInterceptor;
import com.lengdanran.restfulcrud.locale.MyLocaleResolver;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Classname MyConfig
 * @Description TODO
 * @Date 2020/4/9 13:51
 * @Created by ASUS
 */
//使用WebMvcConfigurer可以扩展MVC的功能
@Configuration
public class MyConfig implements WebMvcConfigurer {
//    扩展方法
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
//        地址的映射
        WebMvcConfigurer adapter;
        adapter = new WebMvcConfigurer(){
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
//                浏览器的/login.html和/请求映射到thymeleaf的login.html界面
                registry.addViewController("/login.html").setViewName("login");
                registry.addViewController("/").setViewName("login");
//                registry.addViewController("/user/login").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
                registry.addViewController("/list.html").setViewName("/emp/list");
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                String[] url = {"/main.html","/list.html","/emps"};
                List<String> urls = new ArrayList<>(url.length);
                Collections.addAll(urls,url);
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns(urls);
            }
        };
        return adapter;
    }
//    注册国际化信息
    @Bean
    public LocaleResolver myLocaleResolver(){
        return new MyLocaleResolver();
    }

}
