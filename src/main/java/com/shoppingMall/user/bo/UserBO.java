package com.shoppingMall.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
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
	
	public User addUser(String name, String email) {
		return userRepository.save(User.builder()
				.name(name)
				.email(email)
				.build());
	}
	
	public User getUserByUserIdAndUserPwd(String userId, String userPwd) {
		return userRepository.findByUserIdAndUserPwd(userId,userPwd);
	}
	
	public User getUserByNameAndEmail(String name, String email) {
		return userRepository.findByNameAndEmail(name, email);
	}
	
	public User updatedUserPwd(String email, String userPwd) {
		
		User user = userRepository.findByEmail(email);
		
		if (user != null) {
			user.setUserPwd(userPwd);
			user.setTempPwd(true);
			
			return userRepository.save(user);			
		} else {
			return null;
		}
	}
	
	public User updateUser(String userId, String userPwd, String phone, String address ) {
		User user = userRepository.findByUserId(userId);
		
		if (user != null) {
			user.setUserPwd(userPwd);
			user.setPhone(phone);
			user.setAddress(address);
			
			return userRepository.save(user);			
		} else {
			return null;
		}
	}
	
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	

}
