package ufpb.project.acervodigital.services;

import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ufpb.project.acervodigital.DTOs.AuthRequestDTO;
import ufpb.project.acervodigital.DTOs.AuthResponseDTO;
import ufpb.project.acervodigital.exception.ItemNotFoundException;
import ufpb.project.acervodigital.exception.UserExistsException;
import ufpb.project.acervodigital.models.User;
import ufpb.project.acervodigital.repositores.UserRepository;
import ufpb.project.acervodigital.security.JwtService;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponseDTO login(@Valid AuthRequestDTO authRequestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequestDTO.getEmail(),
                        authRequestDTO.getSenha()
                )
        );

        var user = userRepository.findByEmail(authRequestDTO.getEmail())
                .orElseThrow(() -> new ItemNotFoundException("suário não encontrado após autenticação bem-sucedida."));

        String token = jwtService.generateToken(user);

        return new AuthResponseDTO(token);
    }

}
