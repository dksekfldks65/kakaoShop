package com.kakao.controller;

import com.kakao.domain.User;
import com.kakao.service.UserService;
import com.kakao.service.LoginService;

import org.apache.catalina.startup.UserConfig;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
	@Autowired
	LoginService loginService;
    
    //메인페이지 이동
    @RequestMapping("/")
    public String home(HttpSession session, Model model) {
    	Object sessionInfo = session.getAttribute("login");
    	if(sessionInfo == null) {
    		model.addAttribute("login", null);
    	}
    	else {
    		model.addAttribute("login", sessionInfo);
    	}
        return "main";
    }
    
	//로그인 페이지 이동
    @RequestMapping("loginForm")
    public String loginForm() {
        return "loginForm";
    }
    
    //로그인 기능 처리
    @RequestMapping(value = "login")
    String login(HttpServletRequest request,HttpServletResponse response, final HttpSession session, Model model) throws Exception {
    	
        if ( session.getAttribute("login") != null ){
            session.removeAttribute("login"); // 기존에 login이란 세션 값이 존재한다면 기존값 제거
        }

    	String userId = request.getParameter("email");
    	String password = request.getParameter("password");
    	if(loginService.checkUser(userId, password)) { //회원 등록 확인 성공
    		session.setAttribute("login", userId); //세션에 로그인 정보 저장
    		model.addAttribute("login", userId);
            return "main";
    	}		
    	else {
    		return "hello";
    	}
    }   
    
    //로그아웃 기능 처리
    @RequestMapping(value="/logout")
    public String logout(HttpSession session, Model model) {
    	session.removeAttribute("login"); // 하나씩 하려면 이렇게 해도 됨.
    	model.addAttribute("login", null);
        return "main"; 
    }
}
