package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Component
public class SalesTaxCalculator implements TaxCalculator {

	private double taxRate;	// default rate

    public SalesTaxCalculator() {
	}

	@Autowired
	public SalesTaxCalculator(@Value("${tax.rate}") double taxRate) {
		this.taxRate = taxRate;
	}

	public double calculateTax(double amount) {
		return amount * taxRate;
	}	


    //  TODO-13: (OPTIONAL) The two TaxCalculator beans should be mutually exclusive.
    //  Add a @Profile("!compound") annotation to this SalesTaxCalculator class.
    //  Organize imports, save work, re-run the RegisterCompoundTest.  It should PASS.
    //  Be sure that the original RegisterTest still passes as well.

}
