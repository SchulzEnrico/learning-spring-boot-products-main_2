package com.example.learningspringbootproducts.product;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {
    ProductRepo productRepo = mock(ProductRepo.class);
    IdService idService = new IdService();
    ProductService productService = new ProductService(productRepo,idService);

    @Test
    void getAllProducts() {
        // GIVEN
        List<Product> expected = List.of(
                new Product("p1","iPhone",23.00),
                new Product("p2","test",19.78)
        );
        // WHEN
        when(productRepo.findAll()).thenReturn(expected);
        List<Product> actual = productService.getAllProducts();
        // THEN
        verify(productRepo).findAll();
        assertEquals(expected,actual);
    }

    @Test
    void findProductById() {
    }

    @Test
    void removeProductById() {
    }

    @Test
    void updateProductById() {
    }
}