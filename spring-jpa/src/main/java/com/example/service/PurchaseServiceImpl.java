package com.example.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.PurchaseDao;
import com.example.domain.Purchase;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseDao purchaseDao;

    @Autowired
    public PurchaseServiceImpl(PurchaseDao purchaseDao) {
        this.purchaseDao = purchaseDao;
    }

    public void savePurchase(Purchase purchase) {
        purchaseDao.savePurchase(purchase);
    }

    public List<Purchase> findAllPurchases() {
        return purchaseDao.getAllPurchases();
    }

    public Purchase findPurchase(int id) {
        return purchaseDao.getPurchase(id);
    }

    public Purchase findPurchase(String name, Date date) {
        return purchaseDao.getPurchase(name, date);
    }

}
