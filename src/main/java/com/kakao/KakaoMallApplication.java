package com.kakao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class KakaoMallApplication{
	public static void main(String[] args) {
        SpringApplication.run(KakaoMallApplication.class, args);
   }
}