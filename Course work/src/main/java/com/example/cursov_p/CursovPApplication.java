package com.example.cursov_p;

import lombok.val;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import java.util.Properties;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class CursovPApplication {

    public static void main(String... args) {
        val props = new Properties();
        props.setProperty("spring.session.jdbc.initialize-schema", "always");

        val app = new SpringApplication(CursovPApplication.class);
        app.setDefaultProperties(props);
        app.run();
    }
}
