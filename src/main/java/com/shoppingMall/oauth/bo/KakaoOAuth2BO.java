package com.shoppingMall.oauth.bo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.shoppingMall.user.entity.User;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class KakaoOAuth2BO {
	
	@Value("${kakao.api_key}")
	private String kakaoApiKey;

	@Value("${kakao.redirect_uri}")
	private String kakaoRedirectUri;
	
	
	// 카카오로그인1. 인가 코드 받기 
	public String getKakaoSignInUrl() {
		StringBuffer url = new StringBuffer("https://kauth.kakao.com/oauth/authorize?response_type=code");
		url.append("&client_id=").append(kakaoApiKey);
		url.append("&redirect_uri=").append(kakaoRedirectUri);
		return url.toString();
	}
	
	

	// 카카오로그인2. 토큰 받기 
	public String getAccessToken(String code) {
		String accessToken="";
		String refreshToken="";
		
		try {
				URL url = new URL("https://kauth.kakao.com/oauth/token");
		        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        
		        //필수 헤더 세팅
		        conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		        conn.setDoOutput(true); //OutputStream으로 POST 데이터를 넘겨주겠다는 옵션
		        
		        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		        StringBuilder sb = new StringBuilder();
		        
		        sb.append("grant_type=authorization_code");
		        sb.append("&client_id=").append(kakaoApiKey);
		        sb.append("&redirect_uri=").append(kakaoRedirectUri);
		        sb.append("&code=").append(code);
		        bw.write(sb.toString());
		        bw.flush();
		        
		        int responseCode = conn.getResponseCode(); // 200이면 성공
		        log.info("[KakaoApi.getAccessToken] responseCode = {}", responseCode);
		        
		        BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
		        String message = "";
		        String result = "";
		        while((message = br.readLine()) != null) {
		        	result += message;
		        }
		        
		        JsonParser parser = new JsonParser();
		        JsonElement element = parser.parse(result);
		        accessToken = element.getAsJsonObject().get("access_token").getAsString();
		        refreshToken = element.getAsJsonObject().get("refresh_token").getAsString();
		        
		        br.close();
		        bw.close();
		        
		    } catch (Exception e){
		        e.printStackTrace();
		    }
		     
		return accessToken;		
	}
	
	// 카카오로그인3. 유저 정보 가져오기 
	public User getKakaoUserInfo(String accessToken) {
		
		User user = new User();
		
		try {
			URL url = new URL("https://kapi.kakao.com/v2/user/me");
			 HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setRequestMethod("POST");
		        conn.setRequestProperty("Authorization", "Bearer " + accessToken);
		        conn.setRequestProperty("charset", "UTF-8");
		        
		        int responseCode = conn.getResponseCode(); // 200이면 성공
		        log.info("[KakaoApi.getUserInfo] responseCode : {}",  responseCode);
			
		        BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
		        String message = "";
		        String result = "";
		        while((message = br.readLine()) != null) {
		        	result += message;
		        }
		        
		        JsonParser parser = new JsonParser();
		        JsonElement element = parser.parse(result);
		        JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
		        JsonObject kakaoAccount = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
		        
		        String id = element.getAsJsonObject().get("id").getAsString();
		        String nickname = properties.getAsJsonObject().get("nickname").getAsString();
		        String email = element.getAsJsonObject().get("email").getAsString();
		        
		        log.info("[ Kakao Service ] Auth ID ---> {} ",id);
		        log.info("[ Kakao Service ] nickname ---> {} ", nickname);
		        log.info("[ Kakao Service ] email ---> {} ", email);
		        
		        user.setName(nickname);
		        user.setEmail(email);
		        br.close();
		        
		} catch (Exception e) {
				e.printStackTrace();
		}
		
		return user;
	}
	
	
	// 카카오 로그아웃 
	public void kakaoLogout(String accessToken) {
		try {
			URL url = new URL("https://kapi.kakao.com/v2/user/me");
			 HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setRequestMethod("POST");
		        conn.setRequestProperty("Authorization", "Bearer " + accessToken);
		        
		        int responseCode = conn.getResponseCode(); // 200이면 성공
		        log.info("[KakaoApi.kakaoLogout] responseCode : {}",  responseCode);
		        
		        BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
		        String message = "";
		        String result = "";
		        while((message = br.readLine()) != null) {
		        	result += message;
		        }
		        
		} catch (Exception e) {
				e.printStackTrace();
		}
	}
}
