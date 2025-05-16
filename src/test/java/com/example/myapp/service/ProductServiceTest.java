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
import java.util.Optional;

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


    @Test
    void updateProduct_shouldUpdateExistingProduct() {
        // Arrange
        String productId = "123";
        Product existingProduct = product;
        Product expectedProduct = product1;

        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(any(Product.class))).thenReturn(expectedProduct);

        // Act
        Product result = productService.updateProduct(productId, expectedProduct);

        // Assert
        assertNotNull(result);
        assertEquals("Hpd", result.getName());
        assertEquals("well designed", result.getDescription());
        assertEquals(8.0, result.getPrice());

        verify(productRepository).findById(productId);
        verify(productRepository).save(existingProduct);
    }

    @Test
    public void deleteProductTest(){
        //Arrange
        when(productRepository.findById("1")).thenReturn(Optional.of(product1));
        doNothing().when(productRepository).delete(product1);

        //Act
        productService.deleteProduct("1");

        //Assert
        verify(productRepository).findById("1");
        verify(productRepository).delete(product1);

    }





}
