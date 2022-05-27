package com.enigma.enigmatboot.service;

import com.enigma.enigmatboot.entity.Product;

import java.util.List;

public interface ProductService {
    public Product saveProduct(Product product);
    public Product getProductById(String id);
    public List<Product> getAllProduct();
    public void deleteProduct(String id);
}
