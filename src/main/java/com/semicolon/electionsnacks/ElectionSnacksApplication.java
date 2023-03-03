package com.semicolon.electionsnacks;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@Configuration
@ComponentScan(basePackages = "com.semicolon.electionsnacks.configurations")
public class ElectionSnacksApplication {
    public static void main(String[] args) {
        SpringApplication.run(ElectionSnacksApplication.class, args);
    }

}
