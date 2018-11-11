package com.kakao.service;

import com.kakao.domain.Product;
import com.kakao.domain.Sale;
import com.kakao.domain.User;
import com.kakao.service.SHA256Password;
import com.kakao.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {
	@Autowired
	ProductRepository productRepository;
	
	//모든 제품 반환
	public List<Product> findAll(){
		return productRepository.findAll();
	}
	
	//제품번호를 이용하여 하나의 제품 반환
	public Product findOne(String productId) {
		return productRepository.findOne(productId);
	}
}
