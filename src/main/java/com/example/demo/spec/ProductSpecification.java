package com.example.demo.spec;

import com.example.demo.entity.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    public static Specification<Product> nameEqual(String name) {
        return new Specification<Product> () {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("name"), name);
            }
        };
    }

    public static Specification<Product> priceEqual(int price) {
        return new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("price"), price);
            }
        };
    }


    public static Specification<Product> priceMoreThan(int price) {
        return new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.greaterThan(root.get("price"), price);
            }
        };
    }

    // 상품 이름과 가격을 and로 묶음
    public static Specification<Product> equalNameAndPrice(String name, int price) {
        return Specification.where(nameEqual(name)).and(priceEqual(price));
    }

    // 상품 이름과 가격을 or로 묶음
    public static Specification<Product> equalNameOrPrice(String name, int price) {
        return Specification.where(nameEqual(name)).or(priceEqual(price));
    }

    //상품 카테고리
}
