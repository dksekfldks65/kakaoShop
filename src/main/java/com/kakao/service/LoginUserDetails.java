package com.kakao.service;

import com.kakao.domain.User;
import lombok.Data;

@Data
public class LoginUserDetails{
	private final User user;
	
	//로그인 유저 정보
	public LoginUserDetails(User user) {
		this.user = user;
	}

}
