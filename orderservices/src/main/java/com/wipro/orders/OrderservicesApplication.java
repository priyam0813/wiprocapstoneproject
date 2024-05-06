package com.wipro.orders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrderservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderservicesApplication.class, args);
	}

}
