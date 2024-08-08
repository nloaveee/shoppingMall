package com.shoppingMall.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryptor {
	public static String encryptPassword(String password) {
			
			try {
				MessageDigest md = MessageDigest.getInstance("SHA-256");
				md.update(password.getBytes());
				
				byte byteData[] = md.digest();
				
				// 바이트를 헥사로 변환
				StringBuilder sb = new StringBuilder();
				for(int i = 0; i<byteData.length; i++) {
					sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
				}
				
				return sb.toString();
				
			} catch (NoSuchAlgorithmException e) {
				throw new RuntimeException();
			}
			
			
		}

}
