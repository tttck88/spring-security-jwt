package com.example.springsecurity.app.user.service;

import com.example.springsecurity.app.user.domain.MyUserDetails;
import com.example.springsecurity.app.user.repository.UserRepository;
import com.example.springsecurity.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.thymeleaf.model.IProcessingInstruction;

import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class UserDetailsService {
	private final UserRepository userRepository;

	public MyUserDetails loadUserByUsername(String email) {
		return userRepository.findByEmail(email)
			.map(u -> new MyUserDetails(u, Collections.singleton(new SimpleGrantedAuthority(u.getRole().getValue()))))
			.orElseThrow(() -> new UserNotFoundException(email));
	}
}
