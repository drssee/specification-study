package com.example.demo.spec;

import com.example.demo.entity.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    // spec은 criteria 사용 predicate(criteria 동적쿼리 조건) 반환함
    // JpaSpecificationExecutor 를 이용해 spec 실행

    // spec을 사용하면 criteria를 이용해 동적 쿼리를 생성하는 셈임

    public static Specification<Product> equalCategory(String category) {
        return new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (category == null) {
                    return criteriaBuilder.conjunction(); // 항상 true
                }
                return criteriaBuilder.equal(root.get("category"), category);
            }
        };
    }

    // 넘어온 파라미터 크기 비교해서 동적쿼리 생성
    public static Specification<Product> moreThan(double price) {
        return new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.greaterThan(root.get("price"), price);
            }
        };
    }

    // spec and or 사용법이랑 다른테이블이랑 조인해서 동적쿼리 짜는법
}
