package com.shoppingMall.mail.bo;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.shoppingMall.common.PasswordEncryptor;
import com.shoppingMall.mail.Mail;
import com.shoppingMall.user.bo.UserBO;

@Service

public class MailBO {
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private JavaMailSender javaMailSender;
	

	// 메일 생성 & DB 비밀번호 업데이트
	public Mail createMail(String email) {
		
		// 임시 비밀번호 생성
		String tempPwd = UUID.randomUUID().toString().substring(0,12);
		
		Mail mail = new Mail();
		mail.setAddress(email);
		mail.setTitle("shop 임시비밀번호 안내 이메일 입니다.");
		mail.setMessage("안녕하세요\n shop 임시비밀번호 안내 이메일입니다. \n 회원님의 임시 비밀번호는 " + tempPwd+" 입니다.\n 로그인 후 비밀번호 변경해주세요");
		
		// 임시 비밀번호 암호화 후 업데이트 
		String encryptTempPwd = PasswordEncryptor.encryptPassword(tempPwd);
		userBO.updatedUserPwd(email, encryptTempPwd);
		
		return mail;		
	}
	
	// 메일 전송
	public Boolean sendMail(Mail mail) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(mail.getAddress());
		message.setSubject(mail.getTitle());
		message.setText(mail.getMessage());
		message.setFrom("nloave87@gmail.com");
		message.setReplyTo("nloave87@gmail.com");
		javaMailSender.send(message);
		
		return true;
	}
}
	
