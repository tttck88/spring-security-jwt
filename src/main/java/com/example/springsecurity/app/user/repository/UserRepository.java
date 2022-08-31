package com.example.springsecurity.app.user.repository;

import com.example.springsecurity.app.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long> {

	User findByEmailAndPw(String email, String pw);

	Optional<User> findByEmail(String email);
}
