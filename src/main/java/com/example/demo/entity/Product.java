package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int price;

    // 상품은 카테고리를 하나 가지고 있다고 가정
    // 카테고리는 여러 상품에 속할 수 있음
    // 하나의 카테고리에 여러 상품이 속할 수 있으니
    // 상품(1) - 카테고리(다, 관계의주인)
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
