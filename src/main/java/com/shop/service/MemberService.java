package com.shop.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.shop.entity.Member;
import com.shop.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	public Member saveMember(Member member) {
		validateDuplicationMember(member);
		return memberRepository.save(member);
	}

	private void validateDuplicationMember(Member member) {
		Optional<Member> findMember = memberRepository.findByEmail(member.getEmail());

		if (findMember.isPresent()) {
			System.out.println(findMember.get().getName());
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		}

	}

}
