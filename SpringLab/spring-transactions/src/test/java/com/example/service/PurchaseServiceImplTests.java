package com.example.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.example.Config;
import com.example.domain.Purchase;

@SpringJUnitConfig(Config.class)
public class PurchaseServiceImplTests {

    @Autowired
    PurchaseService purchaseService;

    @Autowired
    PlatformTransactionManager transactionManager;

    @Test
    public void testSavePurchase() {

        TransactionStatus status = 
            transactionManager.getTransaction(
                new DefaultTransactionDefinition());

        Purchase p = new Purchase("Praveen", new Date(), "lava lamp");

        purchaseService.savePurchase(p);

        transactionManager.rollback(status);

        Purchase retrievedPurchase = null;
        try {
            retrievedPurchase = purchaseService.findPurchase(p.getCustomerName(), p.getPurchaseDate());
        } catch (EmptyResultDataAccessException e) {
            fail("Could not retrieve the purchase that was just saved.  Was the transaction rolled back?");
        }

        assertThat(retrievedPurchase).isNotNull();
        assertThat(retrievedPurchase.getId()).isNotNull();
        assertThat(retrievedPurchase.getCustomerName()).isNotNull();
        assertThat(retrievedPurchase.getCustomerName()).isEqualTo(p.getCustomerName());
        assertThat(retrievedPurchase.getProduct()).isEqualTo(p.getProduct());
    }


}
