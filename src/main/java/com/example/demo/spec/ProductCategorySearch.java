package com.example.demo.spec;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import jakarta.persistence.criteria.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductCategorySearch {

    public enum KEY {
        PNAME("name"),
        PPRICE("price"),
        CNAME("name"),
        CPRICE("price");

        private String value;

        KEY(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public Subquery<Long> createPorductSubQuery(CriteriaQuery<?> query, CriteriaBuilder builder, Map<String, Object> searchKeyword) {
        // criteria를 이용해 두 엔티티 조인 후 카테고리 이름이 카테고리A 이고 상품 이름이 상품A 인것 조회하기
        // select *
        // from product p join category c on p.category_id = c.id
        // where c.name = '카테고리A' and p.name = '상품A'

        // 메인 쿼리에 서브쿼리를 붙이고, 조인 타입은 Long임
        Subquery<Long> subquery = query.subquery(Long.class);
        // select * from product p
        Root<Product> product = subquery.from(Product.class);
        // join category c on p.category_id = c.id
        Join<Product, Category> categoryJoin = product.join("category", JoinType.INNER);
        // where c.name = '카테고리A' and p.name '상품A'
        List<Predicate> predicateProduct = new ArrayList<>();
        if (searchKeyword.containsKey(KEY.PNAME)) {

        }
        if (searchKeyword.containsKey(KEY.PPRICE)) {

        }
        if (searchKeyword.containsKey(KEY.PNAME)) {

        }
    }
}
