package com.gabriel_sousa.api_scheduling_system.infrastructure.repository;

import com.gabriel_sousa.api_scheduling_system.infrastructure.entity.CustomerAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddressEntity, Long> {
}
