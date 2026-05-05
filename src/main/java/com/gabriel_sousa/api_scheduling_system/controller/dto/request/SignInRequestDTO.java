package com.gabriel_sousa.api_scheduling_system.controller.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SignInRequestDTO(
        @NotNull
        @Size(max = 160)
        @Email
        String email,

        @NotNull
        String password
) {
}
