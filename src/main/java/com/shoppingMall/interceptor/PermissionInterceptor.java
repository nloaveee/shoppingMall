package com.shoppingMall.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PermissionInterceptor implements HandlerInterceptor{
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView mav) {
		
		// view, model 객체가 있다는 건 html이 해석되기 전 
		log.info("[$$$$$$$$$$ postHandle]");
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			Exception ex) {
		
		// html이 완성된 상태
		log.info("[########## afterCompletion]");
		
	}
}
