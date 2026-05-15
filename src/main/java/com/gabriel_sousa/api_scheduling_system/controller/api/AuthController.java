package com.gabriel_sousa.api_scheduling_system.controller.api;

import com.gabriel_sousa.api_scheduling_system.controller.dto.request.CreateUserDTO;
import com.gabriel_sousa.api_scheduling_system.business.service.AuthService;
import com.gabriel_sousa.api_scheduling_system.controller.dto.request.SignInRequestDTO;
import com.gabriel_sousa.api_scheduling_system.controller.dto.response.SignInResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Autorizações", description = "Autorizações do sistama")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Cadastro", description = "Cadastro de novos usuários")
    @ApiResponse(responseCode = "409", description = "Email já vinculado a outro usuário.", content = @Content)
    @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso.", content = @Content)
    @PostMapping("/sign-up")
    public ResponseEntity signUp(@RequestBody @Valid CreateUserDTO dto){
        authService.saveUser(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    };

    @Operation(summary = "Login", description = "Login de usuários")
    @ApiResponse(responseCode = "200", description = "Sucesso ao realizar login.")
    @ApiResponse(responseCode = "403", description = "Login não autorizado", content = @Content)
    @PostMapping("/sign-in")
    public ResponseEntity<SignInResponseDTO> signIn(@RequestBody @Valid SignInRequestDTO dto){
        var token = authService.login(dto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new SignInResponseDTO(token));
    }
}
