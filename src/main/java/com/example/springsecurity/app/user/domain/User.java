package com.example.springsecurity.app.user.domain;

import com.example.springsecurity.app.common.domain.Common;
import com.example.springsecurity.enums.role.UserRole;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "USER")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends Common implements Serializable {

	@Column(nullable = false, unique = true, length = 50)
	private String email;

	@Setter
	@Column(nullable = false)
	private String pw;

	@Setter
	@Column(nullable = false, length = 50)
	@Enumerated(EnumType.STRING)
	private UserRole role;

}
