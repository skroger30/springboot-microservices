package com.example.centralized_config_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class CentralizedConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CentralizedConfigServerApplication.class, args);
	}

}
