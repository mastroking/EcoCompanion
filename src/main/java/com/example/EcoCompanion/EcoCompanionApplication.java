package com.example.EcoCompanion;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.example.EcoCompanion.controller"})
@ComponentScan(basePackages = "com.example.EcoCompanion.security")
public class EcoCompanionApplication {
	public static void main(String[] args) {
		SpringApplication.run(EcoCompanionApplication.class, args);
	}
}
