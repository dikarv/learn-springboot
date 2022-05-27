package com.enigma.enigmatboot.service.impl;

import com.enigma.enigmatboot.entity.PurchaseDetail;
import com.enigma.enigmatboot.repository.PurchaseDetailRepository;
import com.enigma.enigmatboot.service.PurchaseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseDetailServiceImpl implements PurchaseDetailService {

    @Autowired
    PurchaseDetailRepository purchaseDetailRepository;

    @Override
    public PurchaseDetail savePurchaseDetail(PurchaseDetail purchaseDetail) {
        return purchaseDetailRepository.save(purchaseDetail);
    }
}
