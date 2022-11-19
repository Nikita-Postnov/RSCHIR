package org.example.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() {
        val config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://postgres:5432/postgres");
        config.setDriverClassName("org.postgresql.Driver");
        config.setUsername("postgres");
        config.setPassword("postgres");
        return new HikariDataSource(config);
    }

    @Bean
    public LocalSessionFactoryBean entityManagerFactory(DataSource b) {
        val factory = new LocalSessionFactoryBean();
        factory.setDataSource(b);
        factory.setPackagesToScan("org.example.*");

        val properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL92Dialect");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        factory.setHibernateProperties(properties);
        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(LocalSessionFactoryBean a) {
        return new HibernateTransactionManager(Objects.requireNonNull(a.getObject()));
    }
}
