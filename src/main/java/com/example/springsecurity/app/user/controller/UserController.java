package com.example.springsecurity.app.user.controller;

import com.example.springsecurity.app.user.domain.SignUpDTO;
import com.example.springsecurity.app.user.domain.UserListResponseDTO;
import com.example.springsecurity.app.user.service.UserService;
import com.example.springsecurity.utils.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/user")
public class UserController {

	private final BCryptPasswordEncoder passwordEncoder;
	private final UserService userService;

	@PostMapping(value = "/signUp")
	public ResponseEntity<String> signUp(@RequestBody final SignUpDTO signUpDTO) {
		return userService.findByEmail(signUpDTO.getEmail()).isPresent()
			? ResponseEntity.badRequest().build()
			: ResponseEntity.ok(TokenUtils.generateJwtToken(userService.signUp(signUpDTO)));
	}

	@GetMapping(value = "/list")
	public ResponseEntity<UserListResponseDTO> finalAll() {
		final UserListResponseDTO userListResponseDTO = UserListResponseDTO.builder()
			.userList(userService.findAll()).build();

		return ResponseEntity.ok(userListResponseDTO);
	}
}
