package com.lengdanran.restfulcrud.locale;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @Classname MyLocaleResolver
 * @Description TODO
 * @Date 2020/4/9 14:54
 * @Created by ASUS
 */
public class MyLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
//        从请求域中获取区域信息
        String language = request.getParameter("language");
        System.out.println(language);
        Locale locale = Locale.getDefault();//先获取到系统默认的区域环境的信息
        if(!StringUtils.isEmpty(language)){//如果请求域中的区域信息不为空
            String[] s = language.split("_");//分割
            locale = new Locale(s[0],s[1]);//构建locale对象
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
