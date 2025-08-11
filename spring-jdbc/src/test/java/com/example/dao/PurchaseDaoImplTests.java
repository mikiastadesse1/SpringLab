package com.example.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.example.Config;
import com.example.domain.Purchase;

@SpringJUnitConfig(Config.class)
public class PurchaseDaoImplTests {

    @Autowired
    PurchaseDao dao;

    @Test
    public void findAllPurchases() {
        List<Purchase> purchases = dao.getAllPurchases();

        assertThat(purchases).isNotNull();
        assertThat(purchases.size()).isGreaterThan(0);
        Purchase p = purchases.get(0);
        assertThat(p).isNotNull();
        assertThat(p.getId()).isNotNull();
        assertThat(p.getCustomerName()).isNotNull();
        assertThat(p.getCustomerName()).isEqualTo("Bruce");
    }

    @Test
    public void getPurchase() {
        Purchase p = dao.getPurchase(1);
        assertThat(p).isNotNull();
        assertThat(p.getId()).isEqualTo(1);
        assertThat(p.getCustomerName()).isEqualTo("Bruce");
        assertThat(p.getProduct()).isNotNull();
        assertThat(p.getPurchaseDate()).isNotNull();
    }

    @Test
    public void savePurchase() {
        Purchase p = new Purchase();
        p.setCustomerName("Sample");
        p.setProduct("Sample Product");
        Date now = new Date();
        p.setPurchaseDate(now);

        dao.savePurchase(p);

        Purchase saved = dao.getPurchase("Sample", now);
        assertThat(saved).isNotNull();
        assertThat(saved.getCustomerName()).isEqualTo("Sample");
        assertThat(saved.getProduct()).isEqualTo("Sample Product");
        // Fix: compare time values to avoid Date vs Timestamp equality issue
        assertThat(saved.getPurchaseDate().getTime()).isEqualTo(now.getTime());
    }

}
