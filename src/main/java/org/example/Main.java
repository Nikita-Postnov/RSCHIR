package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication //аннотация
public class Main {
    public static void main(String[] args) {
        new SpringApplication(Main.class).run();
    }
}