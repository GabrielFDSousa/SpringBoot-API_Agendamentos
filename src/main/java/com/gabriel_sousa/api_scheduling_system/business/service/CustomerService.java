package com.gabriel_sousa.api_scheduling_system.business.service;

import com.gabriel_sousa.api_scheduling_system.business.mapper.CustomerMapper;
import com.gabriel_sousa.api_scheduling_system.controller.dto.request.CreateCustomerRequestDTO;
import com.gabriel_sousa.api_scheduling_system.controller.dto.request.UpdateCustomerRequestDTO;
import com.gabriel_sousa.api_scheduling_system.controller.dto.response.CustomerResponseDTO;
import com.gabriel_sousa.api_scheduling_system.infrastructure.entity.CustomerEntity;
import com.gabriel_sousa.api_scheduling_system.infrastructure.exception.UniqueKeyViolationException;
import com.gabriel_sousa.api_scheduling_system.infrastructure.repository.CustomerRepository;
import com.gabriel_sousa.api_scheduling_system.infrastructure.repository.specification.CustomerSpecification;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerResponseDTO saveCustomer(CreateCustomerRequestDTO dto){
        if(customerRepository.existsByWhatsapp(dto.whatsapp()))
            throw new UniqueKeyViolationException("Whatsapp já vinculado a outro cliente.");

        var newCustomer = customerRepository.save(
            customerMapper.toEntity(dto)
        );

        return customerMapper.toResponse(newCustomer);
    }

    public Page<CustomerResponseDTO> getAllPaginated(Boolean isActive, Pageable pageable){
        Specification<CustomerEntity> spec = CustomerSpecification.withFilters(isActive);
        Page<CustomerEntity> customers = customerRepository.findAll(spec, pageable);

        return customers.map(customerMapper::toResponse);
    }

    public CustomerResponseDTO updateCustomer(UpdateCustomerRequestDTO dto, Long id){
        try {
            CustomerEntity customer = customerRepository.getReferenceById(id);
            if(customerRepository.existsByWhatsapp(dto.whatsapp()) && !customer.getWhatsapp().equals(dto.whatsapp()))
                throw new UniqueKeyViolationException("Whatsapp já vinculado a outro cliente.");

            customerMapper.updateEntity(dto, customer);
            customer.setUpdatedAt(LocalDateTime.now());
            customerRepository.save(customer);

            return customerMapper.toResponse(customer);
        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException("Cliente não encontrado.");
        }
    };

    public void disableCustomer(Long id){
        changeActiveStatus(id, false);
    }

    public CustomerResponseDTO reactivateCustomer(Long id){
        CustomerEntity customer = changeActiveStatus(id, true);
        return customerMapper.toResponse(customer);
    }

    private CustomerEntity changeActiveStatus(Long customerId, boolean newStatus){
        try {
            CustomerEntity customer = customerRepository.getReferenceById(customerId);
            customer.setIsActive(newStatus);
            customer.setUpdatedAt(LocalDateTime.now());

            return customerRepository.save(customer);
        } catch (EntityNotFoundException e){
            throw new EntityNotFoundException("Cliente não encontrado.");
        }
    }
}
