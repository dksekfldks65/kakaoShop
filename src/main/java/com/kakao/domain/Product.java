package com.kakao.domain;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor //모든 필드를 인자로 받는 생성자 생성
public class Product {
	private String  productId;
	private String  category;
	private String  name;
	private	Integer price;
	private String  desc;
	private Integer reamin;
	private String  url;
}
