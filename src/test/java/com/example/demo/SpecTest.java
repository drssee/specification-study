package com.example.demo;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.spec.ProductSpecification;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SpecTest {

    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    void init() {
        Product product = new Product();
        product.setCategory("category1");
        product.setName("product1");
        product.setPrice(10000);

        Product product1 = new Product();
        product1.setName("product2");
        product1.setPrice(20000);

        productRepository.save(product);
        productRepository.save(product1);
    }

    @AfterEach
    void clear() {
        productRepository.deleteAll();
    }

    @Test
    void equalCategory() {
        Specification<Product> spec = Specification.where(null);

        String keywordCategory = "category1";

        if (keywordCategory != null) {
            spec = ProductSpecification.equalCategory(keywordCategory);
        }

        List<Product> res = productRepository.findAll(spec);

        assertEquals(1, res.size());
        assertEquals(keywordCategory, res.get(0).getCategory());
    }

    @Test
    void moreThan() {
        // 경계값 포함되지 않음
        int keywordPrice = 19999;
        Specification<Product> spec = Specification.where(null);

        if (keywordPrice != 0) {
            spec = ProductSpecification.moreThan(keywordPrice);
        }

        List<Product> res = productRepository.findAll(spec);
        assertEquals(res.size(), 1);

        keywordPrice = 9999;

        if (keywordPrice != 0) {
            spec = ProductSpecification.moreThan(keywordPrice);
        }

        List<Product> res1 = productRepository.findAll(spec);
        assertEquals(res1.size(), 2);
    }
}
