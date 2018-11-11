package com.kakao.domain;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor //모든 필드를 인자로 받는 생성자 생성
public class User {
	private String userId;
	private String password;
	private String name;
	private String gender;
}
