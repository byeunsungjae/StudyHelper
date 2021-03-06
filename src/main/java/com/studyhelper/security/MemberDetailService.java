package com.studyhelper.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.studyhelper.member.dao.MemberRepository;
import com.studyhelper.member.dto.Member;

@Service
public class MemberDetailService implements UserDetailsService {
	private final MemberRepository memberRepo;

	public MemberDetailService(MemberRepository memberRepo) {
		this.memberRepo = memberRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Member> optional = memberRepo.findById(username);
		if (!optional.isPresent()) {
			throw new UsernameNotFoundException(username + "   사용자가 없음");
		} else {
			Member member = optional.get();
			return new SecurityUser(member);
		}
	}

}
