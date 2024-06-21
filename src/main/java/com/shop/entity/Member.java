package com.shop.entity;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.shop.constant.Role;
import com.shop.dto.MemberFormDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;

	private String name;

	@Column(unique = true)
	private String email;

	private String password;

	private String address;

	@Enumerated(EnumType.STRING)
	private Role role;

	public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
		// 비밀번호: memberFormDto에서 비밀번호를 받아온다 -> 암호화된 비밀번호로 만든다 -> 암호화된 비밀번호 바꾼다.
		Member member = Member.builder().name(memberFormDto.getName()).email(memberFormDto.getEmail())
				.password(passwordEncoder.encode(memberFormDto.getPassword())).address(memberFormDto.getAddress())
				.role(Role.USER).build();

		/*
		 * String password = memberFormDto.getPassword(); // memberFormDto에서 비밀번호를 받아온다
		 * password = passwordEncoder.encode(memberFormDto.getPassword()); // 암호화된 비밀번호로
		 * 만든다 member.setPassword(password); // 암호화된 비밀번호로 바꾼다.
		 */

		return member;
	}

}
