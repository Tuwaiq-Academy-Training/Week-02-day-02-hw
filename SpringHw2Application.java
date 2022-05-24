package com.example.springhw2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringHw2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringHw2Application.class, args);
    }

    @Bean
    public  String hey2(){
        return "Reem";
    }

    @Bean
    public  String hey3(String name){
        System.out.println(" name : "+name);
        return "gg";
    }
}
