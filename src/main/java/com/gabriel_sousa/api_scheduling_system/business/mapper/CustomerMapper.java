package com.gabriel_sousa.api_scheduling_system.business.mapper;

import com.gabriel_sousa.api_scheduling_system.controller.dto.request.CreateCustomerRequestDTO;
import com.gabriel_sousa.api_scheduling_system.controller.dto.request.UpdateCustomerRequestDTO;
import com.gabriel_sousa.api_scheduling_system.controller.dto.response.CustomerResponseDTO;
import com.gabriel_sousa.api_scheduling_system.infrastructure.entity.CustomerEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "addresses", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    CustomerEntity toEntity(CreateCustomerRequestDTO dto);

    CustomerResponseDTO toResponse(CustomerEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "addresses", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    void updateEntity(UpdateCustomerRequestDTO dto, @MappingTarget CustomerEntity entity);
}
