package com.gabriel_sousa.api_scheduling_system.business.mapper;

import com.gabriel_sousa.api_scheduling_system.controller.dto.request.CreateUserDTO;
import com.gabriel_sousa.api_scheduling_system.infrastructure.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "passwordHash", source = "password")
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    UserEntity toEntity(CreateUserDTO dto);
}
