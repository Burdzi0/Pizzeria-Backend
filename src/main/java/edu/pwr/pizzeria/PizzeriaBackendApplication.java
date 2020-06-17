package edu.pwr.pizzeria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class PizzeriaBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PizzeriaBackendApplication.class, args);
    }
}
