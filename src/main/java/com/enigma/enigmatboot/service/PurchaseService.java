package com.enigma.enigmatboot.service;

import com.enigma.enigmatboot.entity.Purchase;

public interface PurchaseService {
    Purchase transaction(Purchase purchase);
    Purchase getTransactionByiD(String id);
}
