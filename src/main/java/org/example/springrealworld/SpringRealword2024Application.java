package org.example.springrealworld;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringRealword2024Application {

    @Bean
    CommandLineRunner runner() {
        return args -> {
            System.out.println("Spring Realworld 2024");
        };
    }
    public static void main(String[] args) {
        SpringApplication.run(SpringRealword2024Application.class, args);
    }


}
