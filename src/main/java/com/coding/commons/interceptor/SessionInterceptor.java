package com.coding.commons.interceptor;

import com.coding.user.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author  没想法的岁月
 * @Date 2018/5/2 10:02
 * @Description Session 拦截器
 */

public class SessionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String URI = request.getRequestURI();
        if (URI.indexOf("login")>= 0) {
            return true;
        }
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("useriunfo");
        if (user != null) {
            return true;
        }
        //跳转到的登录页面
        request.getRequestDispatcher("/login").forward(request,response);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
