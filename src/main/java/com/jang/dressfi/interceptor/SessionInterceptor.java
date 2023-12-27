package com.jang.dressfi.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        Object userId = request.getSession().getAttribute("userId");
// login.do �̳� ȸ�������������� �����Ϸ��� ���(/dressfi/member/join.do) �̹� �α��� �Ǿ� �ִ� �ڴ� �α����̳� ������ �ʿ����==>list.do
        if (request.getRequestURI().equals("/dressfi/member/login.do")
                || request.getRequestURI().equals("/dressfi/member/adminlogin.do")
                || request.getRequestURI().equals("/dressfi/member/findId.do")
                || request.getRequestURI().equals("/dressfi/member/findPass.do")
                || request.getRequestURI().equals("/dressfi/member/joinSelect.do")
                || request.getRequestURI().equals("/dressfi/member/join.do")
                || request.getRequestURI().equals("/dressfi/member/mdjoin.do")
                || request.getRequestURI().equals("/dressfi/member/mmjoin.do")
                || request.getRequestURI().equals("/dressfi/member/mfjoin.do")
                || request.getRequestURI().equals("/dressfi/member/mcjoin.do")
                || request.getRequestURI().equals("/dressfi/member/joinPro.do")
                || request.getRequestURI().equals("/dressfi/member/joinPro2.do")
                || request.getRequestURI().equals("/dressfi/member/joinPro3.do")
                || request.getRequestURI().equals("/dressfi/member/editpass.do")
                || request.getRequestURI().equals("/dressfi/member/checkid.do")
                || request.getRequestURI().equals("/dressfi/member/ajaxlogin.do")) {
            if (userId != null) {
                response.sendRedirect(request.getContextPath() + "/");
                return true; // ����
            } else {
                return true; // login.do ����
            }
        }
        if (userId == null) { // url�� /login or join.do �� �ƴ� ��� ��� �α��� �Ǿ� ����������
            response.sendRedirect(request.getContextPath() + "/member/login.do");
            return false; // ���� URL ������ ���� �ʰ� login.do ����
        } else {
            return true; // ���� URL
            // ������ ����
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }
}
