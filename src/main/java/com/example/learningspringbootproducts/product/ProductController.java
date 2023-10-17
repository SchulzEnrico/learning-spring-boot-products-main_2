package com.example.learningspringbootproducts.product;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product findProductById(@PathVariable String id) {
        return productService.findProductById(id);
    }

    @GetMapping("/price/{price}")
    public List<Product> getAllProductsBynPrice(@PathVariable double price) {
        return productService.getAllProductsBynPrice(price);
    }

    @PostMapping
    public Product addProduct(@RequestBody NewProduct product) {
        return productService.addProduct(product);
    }

    @DeleteMapping("/{id}")
    public void removeProductById(@PathVariable String id) {
        productService.removeProductById(id);
    }

    @PutMapping("/{id}")
    public void updateProductById(@PathVariable String id) {
        productService.updateProductById(id);
    }
}
