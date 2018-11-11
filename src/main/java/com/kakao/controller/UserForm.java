package com.kakao.controller;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserForm { //회원 가입시 사용하는 userForm
	@NotNull
	@Size(min=1, max = 127)
	private String userId;
	@NotNull
	@Size(min=1, max = 127)
	private String password;
	@NotNull
	@Size(min=1, max = 127)
	private String name;
	@NotNull
	@Size(min=1, max = 127)
	private String gender;
}
