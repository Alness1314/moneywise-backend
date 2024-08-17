package com.alness.moneywise.purchase.specification;

import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import com.alness.moneywise.purchase.entity.PurchaseEntity;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class PurchaseSpecification implements Specification<PurchaseEntity>{

    @SuppressWarnings("null")
    @Override
    @Nullable
    public Predicate toPredicate(Root<PurchaseEntity> root, @Nullable CriteriaQuery<?> query,
            CriteriaBuilder criteriaBuilder) {
        return null;
    }

    public Specification<PurchaseEntity> getSpecificationByFilters(Map<String, String> params) {

        Specification<PurchaseEntity> specification = Specification.where(null);
        for (Entry<String, String> entry : params.entrySet()) {
            switch (entry.getKey()) {
                case "id":
                    specification = specification.and(this.filterById(entry.getValue()));
                    break;
                case "store":
                    specification = specification.and(this.filterByStore(entry.getValue()));
                    break;
                default:
                    break;
            }
        }
        return specification;
    }

    private Specification<PurchaseEntity> filterById(String id) {
        return (root, query, cb) -> cb.equal(root.<UUID>get("id"), UUID.fromString(id));
    }

    private Specification<PurchaseEntity> filterByStore(String store) {
        return (root, query, cb) -> cb.like(root.<String>get("store"), store);
        
    }
    
}
