package com.kakao.service;

import com.kakao.domain.User;
import com.kakao.service.SHA256Password;
import com.kakao.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
	@Autowired
	UserRepository userRepository;
	
	//로그인 유저 정보 반환
	public LoginUserDetails loadUserByUserId(String userId) {
		User user = userRepository.findOne(userId);
		return new LoginUserDetails(user);
	}
	
	//아이디와 비밀번호가 맞다면 true, 아니면 false 반환
	public Boolean checkUser(String userId, String password) {
		User user = userRepository.findOne(userId);
		if(user == null) {
			return false;
		}
		SHA256Password sha = new SHA256Password();
		String newPassword = sha.LockPassword(password);

		if(user.getPassword().equals(newPassword)) {
			return true;
		}
		else {
			return false;
		}
	}
}
