package com.example.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.Config;

class RegisterTest {

    private static Register register;

    @BeforeAll
    static void setup() {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        register = context.getBean(Register.class);
    }

    @Test
    void testComputerTotal() {
        double result = register.computeTotal(100.00);
        assertThat(result).isEqualTo(105.00);
    }

}
