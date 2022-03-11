package com.ecommerce.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.ecommerce.comum.entity", "com.ecommerce.admin,usuario"})
public class EcommerceBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceBackEndApplication.class, args);
	}

}
