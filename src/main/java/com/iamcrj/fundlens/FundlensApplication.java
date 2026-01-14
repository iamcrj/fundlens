package com.iamcrj.fundlens;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FundlensApplication {

	public static void main(String[] args) {
		SpringApplication.run(FundlensApplication.class, args);
	}

}
