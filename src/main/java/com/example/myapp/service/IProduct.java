package com.example.myapp.service;

import com.example.myapp.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProduct {
    public List<Product> getAllProducts();
    public Optional<Product> getProductById(String id);
    public Product createProduct(Product product);
    public Product updateProduct(String id, Product product);
    public void deleteProduct(String id);

}
