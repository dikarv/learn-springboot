package com.enigma.enigmatboot.service.impl;

import com.enigma.enigmatboot.entity.Product;
import com.enigma.enigmatboot.repository.ProductRepository;
import com.enigma.enigmatboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(String id) {
        return productRepository.findById(id).get();
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}
