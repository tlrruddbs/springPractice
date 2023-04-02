package com.example.teamservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TeamserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeamserviceApplication.class, args);
	}

}
