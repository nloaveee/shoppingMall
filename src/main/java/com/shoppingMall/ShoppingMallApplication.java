package com.shoppingMall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ShoppingMallApplication extends SpringBootServletInitializer{
	
	public static void main(String[] args) {
		SpringApplication.run(ShoppingMallApplication.class, args);
	}

}
