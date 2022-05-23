package com.example.Work;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@SpringBootApplication
public class WorkApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkApplication.class, args);
	}
@Bean
	public String test(){
		return "";
}
	@Bean
	public Date test2(){
		return null;
	}
	@Bean
	public int test3(){
		return 0;
	}
	@Bean
	public double test4(){
		return 0;
	}
}
