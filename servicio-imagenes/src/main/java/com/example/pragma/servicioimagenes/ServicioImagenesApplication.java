package com.example.pragma.servicioimagenes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServicioImagenesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioImagenesApplication.class, args);
	}

}
