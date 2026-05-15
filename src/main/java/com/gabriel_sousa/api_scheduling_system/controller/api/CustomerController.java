package com.gabriel_sousa.api_scheduling_system.controller.api;

import com.gabriel_sousa.api_scheduling_system.business.service.CustomerService;
import com.gabriel_sousa.api_scheduling_system.controller.dto.request.CreateCustomerRequestDTO;
import com.gabriel_sousa.api_scheduling_system.controller.dto.request.UpdateCustomerRequestDTO;
import com.gabriel_sousa.api_scheduling_system.controller.dto.response.CustomerResponseDTO;
import com.gabriel_sousa.api_scheduling_system.infrastructure.security.SecurityConfiguration;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Clientes", description = "Requisições para busca e controle de clientes.")
@SecurityRequirement(name = SecurityConfiguration.SECURITY)
@RequiredArgsConstructor
@RequestMapping("/customer")
@Controller
public class CustomerController {

    private final CustomerService customerService;

    @Operation(summary = "Cadastro de novo cliente.")
    @ApiResponse(responseCode = "201", description = "Cliente cadastrado.")
    @ApiResponse(responseCode = "409", description = "CPF já vinculado a outro cliente.", content = @Content)
    @PostMapping()
    public ResponseEntity<CustomerResponseDTO> createCustomer(@RequestBody @Valid CreateCustomerRequestDTO dto){
        CustomerResponseDTO newCustomer = customerService.saveCustomer(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCustomer);
    };

    @Operation(summary = "Busca paginada de clientes.")
    @ApiResponse(responseCode = "200")
    @GetMapping()
    public ResponseEntity<Page<CustomerResponseDTO>> getAllPaginated(
            @RequestParam(required = false) Boolean isActive,
            @ParameterObject @PageableDefault(size = 20) Pageable pageable
    ){
        Page<CustomerResponseDTO> pageCustomers = customerService.getAllPaginated(isActive, pageable);
        return ResponseEntity.ok(pageCustomers);
    }

    @Operation(summary = "Atualizar cliente")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "404", description = "Cliente não encontrado.")
    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> updateCustomer(
            @RequestBody @Valid UpdateCustomerRequestDTO dto,
            @PathVariable Long id
    ){
        CustomerResponseDTO updatedClient = customerService.updateCustomer(dto, id);
        return ResponseEntity.ok(updatedClient);
    }

    @Operation(summary = "Desativar cliente")
    @ApiResponse(responseCode = "204")
    @ApiResponse(responseCode = "404", description = "Cliente não encontrado.")
    @PatchMapping("/disable/{id}")
    public ResponseEntity disableCustomer(@PathVariable Long id){
        customerService.disableCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Reativar cliente")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "404", description = "Cliente não encontrado.")
    @PatchMapping("/reactivate/{id}")
    public ResponseEntity<CustomerResponseDTO> reactivateCustomer(@PathVariable Long id){
        CustomerResponseDTO customer = customerService.reactivateCustomer(id);
        return ResponseEntity.ok(customer);
    }
}
