package com.gabriel_sousa.api_scheduling_system.infrastructure.repository;

import com.gabriel_sousa.api_scheduling_system.infrastructure.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CustomerRepository extends
        JpaRepository<CustomerEntity, Long>,
        JpaSpecificationExecutor<CustomerEntity> {

    boolean existsByWhatsapp(String whatsapp);
}
