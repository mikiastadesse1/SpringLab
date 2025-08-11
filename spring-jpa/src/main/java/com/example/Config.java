package com.example;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;


@Configuration   
@PropertySource("classpath:app.properties")
@ComponentScan("com.example")
public class Config {

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.HSQL)
            .addScript("classpath:schema.sql") 
            .addScript("classpath:data.sql")   
            .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource ds) {
        var emfb = new	LocalContainerEntityManagerFactoryBean();
        emfb.setDataSource(ds);
        emfb.setPackagesToScan("com.example.domain");

        var adapter = new HibernateJpaVendorAdapter();
        emfb.setJpaVendorAdapter(adapter);

        var props = new Properties();
        props.setProperty("hibernate.show_sql", "true"); 
        props.setProperty("hibernate.format_sql", "true"); 
        props.setProperty("hibernate.use_sql_comments", "true"); 

        emfb.setJpaProperties(props);
        return emfb;
    }  

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

}
