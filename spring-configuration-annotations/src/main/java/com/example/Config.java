package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.example.service.Register;
import com.example.service.SalesTaxCalculator;
import com.example.service.TaxCalculator;


@Configuration   
@PropertySource("classpath:app.properties")
@ComponentScan(basePackages = "com.example.service")

public class Config {

    @Bean
    public SalesTaxCalculator taxCalculator(@Value("${tax.rate}") double taxRate) {
        return new SalesTaxCalculator(taxRate);
    }

    @Bean
    public Register register(TaxCalculator taxCalculator) {
        return new Register(taxCalculator);
    }

}
