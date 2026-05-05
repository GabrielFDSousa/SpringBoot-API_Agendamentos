package com.gabriel_sousa.api_scheduling_system.infrastructure.repository;

import com.gabriel_sousa.api_scheduling_system.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByEmailIgnoreCase(String email);

    UserDetails findByEmailIgnoreCase(String email);
}
