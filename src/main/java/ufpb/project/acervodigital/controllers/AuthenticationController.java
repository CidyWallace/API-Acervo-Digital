package ufpb.project.acervodigital.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ufpb.project.acervodigital.DTOs.AuthRequestDTO;
import ufpb.project.acervodigital.DTOs.AuthResponseDTO;
import ufpb.project.acervodigital.services.AuthenticationService;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Login - Login de usuários", description = "Endpoint para realizar login")
@SecurityRequirement(name = "bearerAuth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    @Operation(summary = "Faz login de um usuário", description = "Faz login de um usuário já cadastrado e retorna um token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login bem sucessido", content = {
                    @Content(
                            schema = @Schema(implementation = AuthResponseDTO.class)
                    )
            }),
            @ApiResponse(responseCode = "500", description = "Foram enviados dados inválidos", content = {
                    @Content(
                            schema = @Schema(implementation = AuthRequestDTO.class)
                    )
            }),
    })
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody AuthRequestDTO authRequestDTO){
        return ResponseEntity.ok(authenticationService.login(authRequestDTO));
    }
}
