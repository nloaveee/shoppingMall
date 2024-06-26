package com.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// 기본 로그인 사용하기
		// http.formLogin(Customizer.withDefaults());
		// http.logout(Customizer.withDefaults());

		// 로그인 처리하기
		http.formLogin(form -> form.loginPage("/member/login").defaultSuccessUrl("/").failureUrl("/member/login/error")
				.usernameParameter("email").passwordParameter("password").permitAll());

		// 각 페이지에 대한 접근 권한 설정
		http.authorizeHttpRequests(request -> request.requestMatchers("/css/**").permitAll()
				.requestMatchers("/", "/member/**", "/item/**", "/images/**").permitAll().requestMatchers("/admin/**")
				.hasRole("ADMIN") // 특정 권한이 있는 사람만 접근 가능
				.anyRequest().authenticated());

		// 예외 처리 - 인증 실패시 뜨는 error
		http.exceptionHandling(exception -> exception.authenticationEntryPoint(new CustomAuthenticationEntryPoint()));

		// 로그아웃
		http.logout(Customizer.withDefaults());

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
