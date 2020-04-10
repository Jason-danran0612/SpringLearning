package com.lengdanran.restfulcrud.component;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Classname LoginHandlerInterceptor 登录检查
 * @Description TODO
 * @Date 2020/4/9 17:28
 * @Created by ASUS
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object logInUser = request.getSession().getAttribute("LogInUser");
        if (logInUser == null) {
//            没有登录成功的记录
            request.setAttribute("msg", "尚未登录，没有权限访问！");
            request.getRequestDispatcher("/login.html").forward(request, response);
            return false;
        } else {
//            存在登录成功的记录
            return true;
        }
    }
}
