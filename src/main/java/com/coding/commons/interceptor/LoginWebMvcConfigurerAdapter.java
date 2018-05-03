package com.coding.commons.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author  没想法的岁月
 * @Date 2018/5/2 14:46
 * @Description 配置拦截器
 */

@Configuration
public class LoginWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //静态资源拦截 不会写
//      registry.addInterceptor(new SessionInterceptor()).addPathPatterns("/static/jquery/**.js","/static/**");
    }
}
