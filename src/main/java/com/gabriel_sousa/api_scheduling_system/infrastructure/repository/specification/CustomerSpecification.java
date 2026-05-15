package com.gabriel_sousa.api_scheduling_system.infrastructure.repository.specification;

import com.gabriel_sousa.api_scheduling_system.infrastructure.entity.CustomerEntity;
import org.springframework.data.jpa.domain.Specification;

public class CustomerSpecification {

    public static Specification<CustomerEntity> withFilters(Boolean isActive){
        return Specification.where(hasActiveStatus(isActive));
    };

    private static Specification<CustomerEntity> hasActiveStatus(Boolean isActive){
        return (root, query, cb)->
                isActive == null ? null : cb.equal(root.get("isActive"), isActive);
    }
}
