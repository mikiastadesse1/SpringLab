package com.example.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.example.Config;

@SpringJUnitConfig(Config.class)
@ActiveProfiles("compound")
public class RegisterCompoundTest {

    @Autowired
    private Register register;

    @Test 
    void testRegister() {
        double result = register.computeTotal(100);
        Assertions.assertThat(result).isEqualTo(100.00 + 8.50);
        // Assertions.assertThat(result).isEqualTo(100.00 + 7.0);
        System.out.println(String.format("The computed total including tax is: %.2f", result));
    }

}
