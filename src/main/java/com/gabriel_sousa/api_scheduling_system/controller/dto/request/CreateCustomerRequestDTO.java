package com.gabriel_sousa.api_scheduling_system.controller.dto.request;

import com.gabriel_sousa.api_scheduling_system.common.RegexPatterns;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateCustomerRequestDTO(
        @NotBlank
        @Size(max = 160)
        String fullName,

        @Pattern(regexp = RegexPatterns.CPF_WITHOUT_MASK,
                message = "O CPF deve conter 11 dígitos numéricos.")
        String cpf,

        @NotBlank
        @Pattern(regexp = RegexPatterns.PHONE_WITHOUT_MASK,
                message = "O whatsapp deve conter 10 ou 11 dígitos numéricos.")
        String whatsapp,

        @Email
        @Size(max = 160)
        String email,

        @Pattern(regexp = RegexPatterns.PHONE_WITHOUT_MASK,
                message = "O telefone deve conter 10 ou 11 dígitos numéricos.")
        String secondaryPhone,

        @Size(max = 400)
        String observation
) {
}
