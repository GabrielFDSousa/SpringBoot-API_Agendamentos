package com.gabriel_sousa.api_scheduling_system.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer_addresses")
public class CustomerAddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "postal_code", length = 8)
    private String postalCode;

    @Column(nullable = false, name = "federal_unit", length = 2)
    private String federalUnit;

    @Column(nullable = false, length = 80)
    private String city;

    @Column(nullable = false, length = 160)
    private String neighborhood;

    @Column(nullable = false, length = 240)
    private String street;

    @Column(length = 10)
    private String number;

    @Column(length = 20)
    private String complement;

    @Column(name = "ref_point", length = 240)
    private String refPoint;

    @ManyToOne()
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;
}
