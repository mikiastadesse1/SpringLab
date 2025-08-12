package com.example.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.PurchaseDao;
import com.example.domain.Purchase;

@Service
@Transactional
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    PurchaseDao purchaseDAO;

    public List<Purchase> findAllPurchases() {
        return purchaseDAO.getAllPurchases();
    }

    public Purchase findPurchase(int id) {
        return purchaseDAO.getPurchase(id);
    }

    public Purchase findPurchase(String name, Date date) {
        return purchaseDAO.getPurchase(name, date);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void savePurchase(Purchase purchase) {
        purchaseDAO.savePurchase(purchase);
    }

}
