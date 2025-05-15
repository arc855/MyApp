package com.example.myapp.serviceimplementaion;

import com.example.myapp.model.Product;
import com.example.myapp.repository.ProductRepository;
import com.example.myapp.service.IProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProduct {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(String productId){
        return productRepository.findById(productId);
    }

    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    public Product updateProduct(String id,Product product){
        Product productToBeUpdated = productRepository.findById(id).orElseThrow();
        productToBeUpdated.setName(product.getName());
        productToBeUpdated.setDescription(product.getDescription());
        productToBeUpdated.setPrice(product.getPrice());
        return productRepository.save(productToBeUpdated);
    }

    public void deleteProduct(String  id){
        Product product = productRepository.findById(id).orElseThrow();
        productRepository.delete(product);
    }
}
