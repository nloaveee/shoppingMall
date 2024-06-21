package com.shop.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.shop.dto.MemberFormDto;
import com.shop.entity.Member;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
class MemberServiceTest {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private MemberService memberService;

	public Member createMember() {
		MemberFormDto dto = MemberFormDto.builder().name("홍길동").email("test@test.com").address("서울시 서초구")
				.password("1111").build();

		Member member = Member.createMember(dto, passwordEncoder);
		return member;
	}

	@Test
	void saveMemberTest() {
		Member member = createMember();
		System.out.println(member);

		Member member1 = memberService.saveMember(member);
		System.out.println(member1);
	}

	@Test
	@DisplayName("중복 회원 예외 발생 테스트")
	void saveMemberTest2() {
		Member member1 = createMember();
		Member member2 = createMember();
		memberService.saveMember(member1);

		Throwable e = assertThrows(IllegalStateException.class, () -> {
			memberService.saveMember(member2);
		});

		Assertions.assertEquals("이미 존재하는 회원입니다.", e.getMessage());

	}
}
