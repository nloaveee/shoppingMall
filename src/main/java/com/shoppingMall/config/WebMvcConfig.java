package com.shoppingMall.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.shoppingMall.common.FileManagerService;
import com.shoppingMall.interceptor.PermissionInterceptor;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	@Autowired
	private PermissionInterceptor interceptor;
	
	// 인터셉터 설정 
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry
		.addInterceptor(interceptor)
		.addPathPatterns("/**") // 인터셉터 하고 싶은 주소 
		.excludePathPatterns("/error","/css/**","/img/**","/shop/user/sign-out"); // 제외하고 싶은 주소 
	}

	// 이미지 path와 서버에 업로드 된 실제 이미지와 매핑 설정 
	@Override 
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry
		.addResourceHandler("/images/**") // web path => http://localhost/images/aaaa_1721209539165/garden-4647544_1280.jpg
		.addResourceLocations("file:///" + FileManagerService.FILE_UPLOAD_PATH); // 실제 이미지 파일 위치 (window는 처음 /// 3개)
	}

}
