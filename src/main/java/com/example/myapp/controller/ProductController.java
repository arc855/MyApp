package com.example.myapp.controller;

import com.example.myapp.model.Product;
import com.example.myapp.serviceimplementaion.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/get")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @PostMapping("/post")
    public Product createProducts(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product productDetails) {
        try {
            Product updatedProduct = productService.updateProduct(id, productDetails);
            return ResponseEntity.ok(updatedProduct);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getByproductId(@PathVariable String id){
        Optional<Product> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id){
        try{
            productService.deleteProduct(id);
            return ResponseEntity.ok(" Product is deleted");
        }catch(RuntimeException exception){
            return ResponseEntity.notFound().build();
        }
    }

}
