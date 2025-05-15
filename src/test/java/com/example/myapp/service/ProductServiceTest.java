package com.example.myapp.service;

import com.example.myapp.model.Product;
import com.example.myapp.repository.ProductRepository;
import com.example.myapp.serviceimplementaion.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

public class ProductServiceTest {
    @InjectMocks
    private ProductService productService;
    @Mock
    private ProductRepository productRepository;
    private Product product;
    private Product product1;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        product = new Product("Hp","well designed",9.0);
        product1 = new Product("Hpd","well designed",8.0);
    }

    @Test
    public void getAllProducts(){
        List<Product> productList = Arrays.asList(product,product1);
        when(productRepository.findAll()).thenReturn(productList);
        List<Product> productsList1= productService.getAllProducts();
        assertEquals(2,productsList1.size());
        assertEquals(productList,productsList1);
        verify(productRepository,times(1)).findAll();

    }

    @Test
    public void createProducts(){
        when(productRepository.save(any(Product.class))).thenReturn(product1);
        Product prod = productService.createProduct(product1);
        assertNotNull(prod);
        assertEquals(product1,prod);
        verify(productRepository,times(1)).save(product1);
    }





}
