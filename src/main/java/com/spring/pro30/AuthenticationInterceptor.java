package com.spring.pro30;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        boolean is_login = session != null && session.getAttribute("login") != null;
        if(is_login) {
            return true;
        }
        String contextPath = request.getContextPath();
        String nextPage = String.format("%s/%s", contextPath, "/login.do");
        response.sendRedirect(nextPage);
        return false;
    }
}
