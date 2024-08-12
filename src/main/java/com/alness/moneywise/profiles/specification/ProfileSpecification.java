package com.alness.moneywise.profiles.specification;

import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import com.alness.moneywise.profiles.entity.ProfileEntity;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class ProfileSpecification implements Specification<ProfileEntity> {
    @SuppressWarnings("null")
    @Override
    @Nullable
    public Predicate toPredicate(Root<ProfileEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }

    public Specification<ProfileEntity> getSpecificationByFilters(Map<String, String> params) {

        Specification<ProfileEntity> specification = Specification.where(null);
        for (Entry<String, String> entry : params.entrySet()) {
            switch (entry.getKey()) {
                case "id":
                    specification = specification.and(this.filterById(entry.getValue()));
                    break;
                case "name":
                    specification = specification.and(this.filterByName(entry.getValue()));
                    break;
                case "enabled":
                    specification = specification.and(this.filterByEnable(entry.getValue()));
                    break;
                default:
                    break;
            }
        }
        return specification;
    }

    private Specification<ProfileEntity> filterById(String id) {
        return (root, query, cb) -> cb.equal(root.<UUID>get("id"), UUID.fromString(id));
    }

    private Specification<ProfileEntity> filterByName(String name) {
        return (root, query, cb) -> {
            return cb.like(root.<String>get("name"), name);
        };
    }

    private Specification<ProfileEntity> filterByEnable(String enabled) {
        return (root, query, cb) -> cb.equal(root.<Boolean>get("enabled"), Boolean.parseBoolean(enabled));
    }

}
