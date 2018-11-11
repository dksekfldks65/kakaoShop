package com.kakao.controller;



import javax.servlet.http.HttpSession;

import com.kakao.domain.Product;
import com.kakao.service.ProductService;
import com.kakao.domain.Sale;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class productController {
	@Autowired
	ProductService productService;
	
	//상품 상세 정보 및 구매 페이지 이동
	@RequestMapping(value = "productInfomation", method = RequestMethod.GET)
	public String requestProductInfo(@RequestParam(value = "data")String data, Model model, HttpSession session) {
		Product product = productService.findOne(data);
		System.out.println(product);
		model.addAttribute("product", product);
		Object sessionInfo = session.getAttribute("login");
    	if(sessionInfo == null)
    		model.addAttribute("login", null);
    	else 
    		model.addAttribute("login", sessionInfo);
		return "productInfo";
	}
	
	//상품 페이지 이동
	@RequestMapping(value = "productList")
	public String requestProductList(HttpSession session, Model model) {
		List<Product> products = productService.findAll();
		model.addAttribute("products", products);
    	Object sessionInfo = session.getAttribute("login");
    	if(sessionInfo == null)
    		model.addAttribute("login", null);
    	else 
    		model.addAttribute("login", sessionInfo);
		return "productList";
	}
}
