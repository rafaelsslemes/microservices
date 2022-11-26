package com.study.mscreditcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsCreditCardApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCreditCardApplication.class, args);
	}

}
