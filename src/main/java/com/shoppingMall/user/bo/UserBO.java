package com.shoppingMall.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingMall.user.entity.User;
import com.shoppingMall.user.repository.UserRepository;

@Service
public class UserBO {
	

	@Autowired
	private UserRepository userRepository;
	
	// 아이디 중복확인
	public User getUserByUserId(String userId) {
		return userRepository.findByUserId(userId);
	}
	
	// 회원가입 
	public User addUser(String userId, String password, String name, String phone, String email, String address) {
		return userRepository.save(User.builder()
				.userId(userId)
				.userPwd(password)
				.name(name)
				.phone(phone)
				.email(email)
				.address(address)
				.build());
	}
	

}
