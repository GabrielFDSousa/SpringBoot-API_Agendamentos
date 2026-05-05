package com.gabriel_sousa.api_scheduling_system.business.service;

import com.gabriel_sousa.api_scheduling_system.business.mapper.UserMapper;
import com.gabriel_sousa.api_scheduling_system.controller.dto.request.CreateUserDTO;
import com.gabriel_sousa.api_scheduling_system.controller.dto.request.SignInRequestDTO;
import com.gabriel_sousa.api_scheduling_system.infrastructure.entity.UserEntity;
import com.gabriel_sousa.api_scheduling_system.infrastructure.exception.UniqueKeyViolationException;
import com.gabriel_sousa.api_scheduling_system.infrastructure.repository.UserRepository;
import com.gabriel_sousa.api_scheduling_system.infrastructure.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public void saveUser(CreateUserDTO dto){
        var exists = userRepository.existsByEmailIgnoreCase(dto.email());
        if(exists) throw new UniqueKeyViolationException("Email já vinculado a outro usuário.");

        var newUser = userMapper.toEntity(dto);
        newUser.setPasswordHash(passwordEncoder.encode(dto.password()));
        userRepository.save(newUser);
    }

    public String login(SignInRequestDTO dto){
        var authenticationToken = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var authentication = authenticationManager.authenticate(authenticationToken);

        return tokenService.generateToken((UserEntity) authentication.getPrincipal());
    }
}
