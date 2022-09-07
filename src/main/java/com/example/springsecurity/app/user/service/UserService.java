package com.example.springsecurity.app.user.service;

import com.example.springsecurity.app.user.domain.SignUpDTO;
import com.example.springsecurity.app.user.domain.User;
import com.example.springsecurity.app.user.repository.UserRepository;
import com.example.springsecurity.enums.role.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;

	public User signUp(final SignUpDTO signUpDTO) {
		final User user = User.builder()
			.email(signUpDTO.getEmail())
			.pw(passwordEncoder.encode(signUpDTO.getPw()))
			.role(UserRole.ROLE_USER)
			.build();

		return userRepository.save(user);
	}

	public Optional<User>findByEmail(final String email) {
		return userRepository.findByEmail(email);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}
}
