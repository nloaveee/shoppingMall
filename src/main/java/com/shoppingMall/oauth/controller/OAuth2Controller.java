package com.shoppingMall.oauth.controller;

import java.util.Map;

import org.apache.ibatis.io.ResolverUtil.IsA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoppingMall.oauth.bo.GoogleOAuth2BO;
import com.shoppingMall.oauth.bo.KakaoOAuth2BO;
import com.shoppingMall.user.bo.UserBO;
import com.shoppingMall.user.entity.User;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class OAuth2Controller {

    private final KakaoOAuth2BO kakaoOAuth2BO;
    private final UserBO userBO;

    @Autowired
    public OAuth2Controller(KakaoOAuth2BO kakaoOAuth2BO, UserBO userBO) {
        this.kakaoOAuth2BO = kakaoOAuth2BO;
        this.userBO = userBO;
    }
	

	

	
	  @RequestMapping("/login/oauth2/code/kakao")
	    public String kakaoLogin(@RequestParam String code, HttpSession session){
	        // 1. 인가 토근 받기 
		    String accessToken = kakaoOAuth2BO.getAccessToken(code);

	        // 2. 사용자 정보 받기
	        User kakaoUserInfo = kakaoOAuth2BO.getKakaoUserInfo(accessToken);
	        String email = kakaoUserInfo.getEmail();
	        String nickname = kakaoUserInfo.getName();

	        log.info("email = {}", email);
	        log.info("nickname = {}", nickname);
	        log.info("accessToken = {}", accessToken);
	        
	        User user = userBO.getUserByEmail(email);
	        
	        if (user == null) {
	        	user = userBO.addUser(email,nickname);
	        }
	        

	        return "shop/main";
	    }
	
}
	
