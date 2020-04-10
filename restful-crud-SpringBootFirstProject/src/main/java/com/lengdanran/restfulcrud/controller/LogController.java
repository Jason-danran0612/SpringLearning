package com.lengdanran.restfulcrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Classname LogController
 * @Description TODO
 * @Date 2020/4/9 16:15
 * @Created by ASUS
 */
@Controller
public class LogController {

    @RequestMapping(value = "/user/login" ,method = RequestMethod.POST)
    public String Login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map,HttpSession session){
        if("admin".equals(username) && "123456".equals(password)){
            session.setAttribute("LogInUser",username);
            return "redirect:/main.html";//重定向防止数据重复提交
        }else {
            map.put("msg","用户名或密码错误！！");
            return "/login.html";
        }
    }
}
