package com.example.employeeservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class MicroServicesSpringbootEmployeeServiceApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
//	@Bean
//	public RestTemplate restTemplate() {
//		return new RestTemplate();
//	}
	
//	@Bean
//	public WebClient webClient() {
//		return  WebClient.builder().build();
//	}
	
	public static void main(String[] args) {
		SpringApplication.run(MicroServicesSpringbootEmployeeServiceApplication.class, args);
	}

}
