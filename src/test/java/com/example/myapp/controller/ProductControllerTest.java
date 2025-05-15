package com.example.myapp.controller;

import com.example.myapp.model.Product;
import com.example.myapp.serviceimplementaion.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductControllerTest {
    @InjectMocks
    private ProductController productController;
    @Mock
    private ProductService productService;

    private Product product1;
    private Product product2;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        product1 = new Product("Laptop", "High performance laptop",  999.99);
        product2 = new Product("phone", "Smart phone" , 699.99);
    }

    @Test
    public void getAllProductsTest(){
        //Arrange
        List<Product> products = Arrays.asList(product1, product2);
        when(productService.getAllProducts()).thenReturn(products);

        //act
        List<Product> result = productController.getAllProducts();

        //assert
        assertEquals(2,result.size());
        verify(productService,times(1)).getAllProducts();
    }

    @Test
    public void getProductById(){
        // Arrange
        when(productService.getProductById("1")).thenReturn(Optional.of(product1));

        // Act
        ResponseEntity<Product> response = productController.getProductById("1");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product1, response.getBody());
        verify(productService, times(1)).getProductById("1");
    }

    @Test
    public void createProductTest(){
        //arrange
        when(productService.createProduct(product1)).thenReturn(product1);

        //act
        Product product = productController.createProducts(product1);

        //assert
        assertEquals(product1,product1);
    }

    @Test
    public void updateProductTest(){
        when(productService.updateProduct("1",product1)).thenReturn(product1);
        ResponseEntity<Product> response = productController.updateProduct("1",product1);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(product1,response.getBody());
        verify(productService, times(1)).updateProduct("1",product1);

    }

    @Test
    public void deleteById(){
        //arrange
        // Arrange
        doNothing().when(productService).deleteProduct("1");

        // Act
        ResponseEntity<String> response = productController.deleteById("1");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(productService, times(1)).deleteProduct("1");
    }

}
