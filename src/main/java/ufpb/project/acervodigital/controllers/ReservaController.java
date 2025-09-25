package ufpb.project.acervodigital.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ufpb.project.acervodigital.DTOs.ReservaResponseDTO;
import ufpb.project.acervodigital.models.Reserva;
import ufpb.project.acervodigital.models.User;
import ufpb.project.acervodigital.services.ReservaService;

import java.net.URI;

@RestController
@RequestMapping("/api/holds")
@Tag(name = "Reserva", description = "Endpoints para criar e cancelar reservas de um usuário logado")
@SecurityRequirement(name = "bearerAuth")
public class ReservaController {

    private final ReservaService reservaService;
    private final ModelMapper modelMapper;

    public ReservaController(ReservaService reservaService, ModelMapper modelMapper) {
        this.reservaService = reservaService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/{id_ativo}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @Operation(summary = "Cria uma nova reserva", description = "Cria uma nova reserva para o usuário logado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "reserva criada com sucesso",
            content = {
                    @Content(
                            schema = @Schema(implementation = ReservaResponseDTO.class)
                    )
            }),
            @ApiResponse(responseCode = "404", description = "Ativo Digital não encontrado")
    })
    public ResponseEntity<ReservaResponseDTO> createReserva(@PathVariable Long id_ativo, Authentication authentication) {
        var user = (User) authentication.getPrincipal();
        var reserva = reservaService.criaReserva(id_ativo, user);
        var responseDTO = convertToDTO(reserva);
        responseDTO.setPosicaoFila(reservaService.PosicaoReserva(reserva));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}/user/{userId}").buildAndExpand(reserva.getId()).toUri();
        return ResponseEntity.created(location).body(responseDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @Operation(summary = "Cancela a reserva de um usuário", description = "Cancela a reserva especificada pelo id do usuário logado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Reserva não encontrada")
    })
    public ResponseEntity<Void> cancelarReserva(@PathVariable Long id, Authentication authentication) {
        var user = (User) authentication.getPrincipal();
        reservaService.cancelaReserva(id, user.getId());
        return ResponseEntity.noContent().build();
    }

    private ReservaResponseDTO convertToDTO(Reserva reserva) {
        return modelMapper.map(reserva, ReservaResponseDTO.class);
    }
}
