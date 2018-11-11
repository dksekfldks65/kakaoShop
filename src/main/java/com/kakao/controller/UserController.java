package com.kakao.controller;

import com.kakao.domain.User;
import com.kakao.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	@ModelAttribute
	UserForm setUpForm() {
		return new UserForm();
	}

	//회원 가입 페이지로 전환
	@RequestMapping(value = "users",method = RequestMethod.GET)
	public String list(Model model) {
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		return "enroll";
	}
	
	//유저 생성 컨트롤
    @RequestMapping(value = "/users/create", method = RequestMethod.POST)
    String create(@Validated UserForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return list(model);
        }
        User user = new User();
        BeanUtils.copyProperties(form, user);
        userService.create(user);
        return "redirect:/loginForm";
    }
	
}
