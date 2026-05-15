package com.gabriel_sousa.api_scheduling_system.controller.dto.response;

public record CustomerResponseDTO(
        Long id,
        String fullName,
        String cpf,
        String whatsapp,
        String email,
        String secondaryPhone,
        String observation,
        String createdAt,
        String updatedAt
) {
}
