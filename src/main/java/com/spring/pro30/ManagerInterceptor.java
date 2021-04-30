package com.spring.pro30;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ManagerInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        boolean is_login = session != null && session.getAttribute("login") != null;
        if(is_login) {
            MemberVO memberVO = (MemberVO) session.getAttribute("login");
            boolean is_admin = "admin".equals(memberVO.getId());
            if(is_admin)
                return true;
        }
        String contextPath = request.getContextPath();
        String nextPage = String.format("%s", contextPath);
        response.sendRedirect(nextPage);
        return false;
    }
}



