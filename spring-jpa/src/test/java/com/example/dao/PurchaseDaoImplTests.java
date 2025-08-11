package com.example.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import com.example.Config;
import com.example.domain.Customer;
import com.example.domain.Purchase;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@SpringJUnitConfig(Config.class)
@Transactional
public class PurchaseDaoImplTests {

    @PersistenceContext
    EntityManager em;

    @Autowired
    PurchaseDao repo;

    @Test
    public void findAllPurchases() {
        List<Purchase> purchases = repo.getAllPurchases();

        assertThat(purchases).isNotNull();
        assertThat(purchases.size()).isGreaterThan(0);
        Purchase p = purchases.get(0);
        assertThat(p).isNotNull();
        assertThat(p.getId()).isNotNull();
        assertThat(p.getCustomer().getName()).isNotNull();
        assertThat(p.getCustomer().getName()).isEqualTo("Bruce");
    }

    @Test
    public void getPurchase() {
        Purchase p = repo.getPurchase(1);
        assertThat(p).isNotNull();
        assertThat(p.getId()).isEqualTo(1);
        assertThat(p.getCustomer()).isNotNull();
        assertThat(p.getCustomer().getName()).isEqualTo("Bruce");
        assertThat(p.getProduct()).isNotNull();
        assertThat(p.getPurchaseDate()).isNotNull();
    }

    @Test
    public void testSaveAndFind() {
        // Get an existing customer:
        Customer c = em.find(Customer.class, 1);

        // Create a new purchase:
        Purchase p = new Purchase();
        p.setCustomer(c);
        p.setProduct("Incan ceremonial headmask");
        Date now = new Date();
        p.setPurchaseDate(now);

        // Save
        repo.savePurchase(p);

        // Clear persistence context
        em.clear();

        // Find
        Purchase found = repo.getPurchase(c.getName(), now);

        // Assert
        assertThat(found).isNotNull();
        assertThat(found.getCustomer().getName()).isEqualTo(c.getName());
        assertThat(found.getProduct()).isEqualTo("Incan ceremonial headmask");
        assertThat(found.getPurchaseDate().getTime()).isEqualTo(now.getTime());
    }
}
