package com.example.learningspringbootproducts.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    private final static String BASE_URI = "/api/products";
    @Autowired
    private MockMvc mockMvc; // Um HTTP-Requests zu simulieren
    @Autowired
    private ObjectMapper objectMapper; // Wandelt JSON in Java Objekte um und umgekehrt

    @Test
    @DirtiesContext
    void getAllProducts_whenEntriesInDb_expectStatus200AndReturnEntriesAsJson() throws Exception {
        NewProduct product = new NewProduct("TestProduct", 0.99);
        String productAsJson = objectMapper.writeValueAsString(product);

        MvcResult result = mockMvc
                            .perform(post(BASE_URI)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(productAsJson)
                )
                .andExpect(status().isOk())
                .andReturn();

        Product savedProduct = objectMapper.readValue(result.getResponse().getContentAsString(), Product.class);

        List<Product> products = List.of(savedProduct);
        String productsAsJson = objectMapper.writeValueAsString(products);

        mockMvc.perform(get(BASE_URI))
                .andExpect(status().isOk())
                .andExpect(content().json(productsAsJson));
    }

    @Test
    @DirtiesContext
    void findProductById() throws Exception {
        NewProduct product = new NewProduct("TestProduct", 0.99);
        String productAsJson = objectMapper.writeValueAsString(product);

        MvcResult result = mockMvc
                .perform(post(BASE_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productAsJson)
                )
                .andExpect(status().isOk())
                .andReturn();

        Product savedProduct = objectMapper.readValue(result.getResponse().getContentAsString(), Product.class);
        String savedProductAsJson = objectMapper.writeValueAsString(savedProduct);

        mockMvc.perform(get(BASE_URI + "/" + savedProduct.id()))
                .andExpect(status().isOk())
                .andExpect(content().json(savedProductAsJson));
    }

    @Test
    @DirtiesContext
    void getAllProductsByPrice_whenEntriesInDb_expectStatus200AndReturnEntriesAsJson() throws Exception {
        NewProduct product = new NewProduct("TestProduct", 0.99);
        String productAsJson = objectMapper.writeValueAsString(product);

        MvcResult result = mockMvc
                .perform(post(BASE_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productAsJson)
                )
                .andExpect(status().isOk())
                .andReturn();

        Product savedProduct = objectMapper.readValue(result.getResponse().getContentAsString(), Product.class);

        List<Product> products = List.of(savedProduct);
        String productsAsJson = objectMapper.writeValueAsString(products);

        mockMvc.perform(get(BASE_URI))
                .andExpect(status().isOk())
                .andExpect(content().json(productsAsJson));
    }

    @Test
    @DirtiesContext
    void addProduct() throws Exception{
        NewProduct product = new NewProduct("addedProduct", 9.99);
        String productAsJson = objectMapper.writeValueAsString(product);

        MvcResult result = mockMvc.perform(post(BASE_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productAsJson)
                )
                .andExpect(status().isOk())
                .andReturn();

        Product savedProduct = objectMapper.readValue(result.getResponse().getContentAsString(), Product.class);

        assertNotNull(savedProduct.id());
        assertNotNull(savedProduct.name());
        assertNotNull(savedProduct.price());
    }

    @Test
    void removeProductById() {
    }

    @Test
    void updateProductById() {
    }
}