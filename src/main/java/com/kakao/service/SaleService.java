package com.kakao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kakao.domain.Product;
import com.kakao.domain.Sale;
import com.kakao.repository.SaleRepository;

@Service
@Transactional
public class SaleService {
	@Autowired
	SaleRepository saleRepository;
	
	//거래 저장
	public void saveSale(Integer num, String productId, String userId, Integer productPrice) {
		Integer sum = num*productPrice;
		Sale sale = new Sale(userId, productId, num, sum);
		saleRepository.saveSale(sale);
	}
	
	//모든 거래 반환
	public List<Sale> findAllSale(String userId){
		return saleRepository.findAllSale(userId);
	}
}
