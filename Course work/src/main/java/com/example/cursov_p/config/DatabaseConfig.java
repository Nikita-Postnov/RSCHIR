package com.example.cursov_p.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@EnableJpaRepositories(basePackages = {"com.example.cursov_p"})
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
    public LocalSessionFactoryBean entityManagerFactory(DataSource dataSource) {
        val properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL92Dialect");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");

        val factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPackagesToScan("com.example.cursov_p.*");
        factoryBean.setHibernateProperties(properties);
        return factoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(LocalSessionFactoryBean factoryBean)
    { return new HibernateTransactionManager(Objects.requireNonNull(factoryBean.getObject())); }
}
