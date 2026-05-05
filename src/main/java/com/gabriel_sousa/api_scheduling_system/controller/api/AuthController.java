package com.gabriel_sousa.api_scheduling_system.controller.api;

import com.gabriel_sousa.api_scheduling_system.controller.dto.request.CreateUserDTO;
import com.gabriel_sousa.api_scheduling_system.business.service.AuthService;
import com.gabriel_sousa.api_scheduling_system.controller.dto.request.SignInRequestDTO;
import com.gabriel_sousa.api_scheduling_system.controller.dto.response.SignInResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller()
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity signUp(@RequestBody @Valid CreateUserDTO dto){
        authService.saveUser(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    };

    @PostMapping("/sign-in")
    public ResponseEntity<SignInResponseDTO> signIn(@RequestBody @Valid SignInRequestDTO dto){
        var token = authService.login(dto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new SignInResponseDTO(token));
    }
}
