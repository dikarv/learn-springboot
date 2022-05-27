package com.enigma.enigmatboot.service.impl;

import com.enigma.enigmatboot.entity.Product;
import com.enigma.enigmatboot.entity.Purchase;
import com.enigma.enigmatboot.entity.PurchaseDetail;
import com.enigma.enigmatboot.repository.PurchaseRepository;
import com.enigma.enigmatboot.service.CustomerService;
import com.enigma.enigmatboot.service.ProductService;
import com.enigma.enigmatboot.service.PurchaseDetailService;
import com.enigma.enigmatboot.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    PurchaseDetailService purchaseDetailService;

    @Autowired
    ProductService productService;

    @Autowired
    CustomerService customerService;

    @Override
    @Transactional
    public Purchase transaction(Purchase purchase) {
        Purchase purchase1 = purchaseRepository.save(purchase);
        for (PurchaseDetail purchaseDetail : purchase1.getPurchaseDetails()) {
            Product product = productService.getProductById(purchaseDetail.getProduct().getId());
            product.setStock(product.getStock() - purchaseDetail.getQuantity());
            productService.saveProduct(product);
            purchaseDetail.setPurchase(purchase1);
            purchaseDetail.setPriceSell(product.getProduct_price().doubleValue() * purchaseDetail.getQuantity());
            purchaseDetailService.savePurchaseDetail(purchaseDetail);
        }
       return purchase1;
    }

    @Override
    public Purchase getTransactionByiD(String id) {
        return purchaseRepository.findById(id).get();
    }
}
