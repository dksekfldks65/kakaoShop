package com.kakao.service;

import com.kakao.domain.User;
import com.kakao.service.SHA256Password;
import com.kakao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	//모든 유저 찾아서 반환
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	//유저 셀렉트
	public User findOne(String userId) {
		return userRepository.findOne(userId);
	}
	
	//유저 생성
	public User create(User user) {
		SHA256Password sha = new SHA256Password();
		String newPassword = sha.LockPassword(user.getPassword());
		user.setPassword(newPassword);
		User updateUser = userRepository.save(user);
		if(updateUser == null)
			return null;
		else
			return updateUser;
	}
	
	//회원 삭제
	public void delete(String userId) {
		userRepository.delete(userId);
	}
	
}
