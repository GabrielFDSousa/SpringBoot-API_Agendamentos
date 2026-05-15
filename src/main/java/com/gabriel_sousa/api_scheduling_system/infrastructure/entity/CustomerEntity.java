package com.gabriel_sousa.api_scheduling_system.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "full_name", length = 160)
    private String fullName;

    @Column(length = 11)
    private String cpf;

    @Column(nullable = false, length = 11, unique = true)
    private String whatsapp;

    @Column(length = 160)
    private String email;

    @Column(name = "secondary_phone", length = 11)
    private String secondaryPhone;

    @Column(length = 400)
    private String observation;

    @Column(name = "is_active")
    private Boolean isActive = true;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<CustomerAddressEntity> addresses = new HashSet<>();
}
