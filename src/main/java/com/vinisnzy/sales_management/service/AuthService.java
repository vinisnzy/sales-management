package com.vinisnzy.sales_management.service;

import com.vinisnzy.sales_management.dto.auth.LoginRequestDTO;
import com.vinisnzy.sales_management.dto.auth.LoginResponseDTO;
import com.vinisnzy.sales_management.dto.auth.RegisterRequestDTO;
import com.vinisnzy.sales_management.dto.auth.RegisterResponseDTO;
import com.vinisnzy.sales_management.enums.UserRole;
import com.vinisnzy.sales_management.exceptions.EntityNotFoundException;
import com.vinisnzy.sales_management.model.User;
import com.vinisnzy.sales_management.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public RegisterResponseDTO register(RegisterRequestDTO data) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        User user = new User(null, data.name(), data.email(), encryptedPassword, UserRole.SELLER);
        repository.save(user);

        return new RegisterResponseDTO(user.getName(), user.getEmail());
    }

    public LoginResponseDTO login(LoginRequestDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        this.authenticationManager.authenticate(usernamePassword);

        User user = repository.findByEmail(data.email())
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + data.email()));

        var token = tokenService.generateToken(user);
        return new LoginResponseDTO(token);
    }
}
