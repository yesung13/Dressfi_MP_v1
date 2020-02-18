package com.jang.dressfi.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SessionInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Object userId = request.getSession().getAttribute("userId");
// login.do 이나 회원가입페이지를 실행하려는 경우(/dressfi/member/join.do) 이미 로그인 되어 있는 자는 로그인이나 가입이 필요없음==>list.do
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
				|| request.getRequestURI().equals("/dressfi/member/ajaxlogin.do"))
		{
			if (userId != null) {
				response.sendRedirect(request.getContextPath() + "/");
				return true; // 실행
			} else {
				return true; // login.do 실행
			}
		}
		if (userId == null) { // url이 /login or join.do 가 아닌 모든 경우 로그인 되어 있지않으면
			response.sendRedirect(request.getContextPath() + "/member/login.do");
			return false; // 현재 URL 페이지 실행 않고 login.do 실행
		} else {
			return true; // 현재 URL
			// 페이지 실행
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}
}
