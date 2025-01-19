package com.example.api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiGatewayApplication {
	
//  	@Bean
//	public HttpClient httpClient() {
//	    return HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
//	}

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}
