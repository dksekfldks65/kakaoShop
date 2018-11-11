package com.kakao.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kakao.domain.Sale;
import com.kakao.service.SaleService;

@Controller
public class SaleController {
	@Autowired
	SaleService saleService;

	
	//구매 이력 페이지 이동
	@RequestMapping(value = "buyHistory")
	public String buyHistory(HttpSession session, Model model) {
    	String userId = (String) session.getAttribute("login");

    	if(userId == null) {
    		model.addAttribute("login", null);
    		return "loginForm";
    	}
    	else {
        	List<Sale> sales = saleService.findAllSale(userId);
        	model.addAttribute("sales", sales);
    		model.addAttribute("login", userId);
    		return "buyHistory";
    	}
	}
	
	//상품 구매 처리
	@RequestMapping(value = "/buyProduct", method = RequestMethod.GET)
	public String buyProduct(@RequestParam(value = "num")Integer num, @RequestParam(value = "productId")String productId,
							@RequestParam(value = "productPrice")Integer productPrice, Model model, HttpSession session) {

	   	String userId = (String) session.getAttribute("login");

    	if(userId == null) {
    		model.addAttribute("login", null);
    		return "loginForm";
    	}
    	else {
    		model.addAttribute("login", userId);
    		saleService.saveSale(num, productId, userId, productPrice);
    		return "main";
    	}
	}
	
	//구매 내력 출력
	@RequestMapping(value="saleTest")
	public String findSale(HttpSession session, Model model){
		String userId = (String) session.getAttribute("login");
		List<Sale> sales = saleService.findAllSale(userId);
		model.addAttribute("sales", sales);
		
		Object sessionInfo = session.getAttribute("login");
    	if(sessionInfo == null) {
    		model.addAttribute("login", null);
    	}
    	else {
    		model.addAttribute("login", sessionInfo);
    	}
		
		return "buyHistory";
	}
}
