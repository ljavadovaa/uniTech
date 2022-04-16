package com.project.unitech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UniTechApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniTechApplication.class, args);
	}

}
