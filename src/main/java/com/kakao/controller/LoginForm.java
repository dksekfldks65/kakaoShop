package com.kakao.controller;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginForm {
	@NotNull
	@Size(min=1, max = 127)
	private String userId;
	@NotNull
	@Size(min=1, max = 127)
	private String password;
}
