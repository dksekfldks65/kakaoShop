package com.kakao.domain;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor //모든 필드를 인자로 받는 생성자 생성
public class Sale {
	private String userId;
	private String productId;
	private Integer saleNum;
	private Integer sumPrice;
}
