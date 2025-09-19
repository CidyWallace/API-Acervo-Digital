package ufpb.project.acervodigital.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ufpb.project.acervodigital.DTOs.ReservaRequestDTO;
import ufpb.project.acervodigital.DTOs.ReservaResponseDTO;
import ufpb.project.acervodigital.models.Reserva;
import ufpb.project.acervodigital.services.ReservaService;

import java.net.URI;

@RestController
@RequestMapping("/api/holds")
public class ReservaController {

    private final ReservaService reservaService;
    private final ModelMapper modelMapper;

    public ReservaController(ReservaService reservaService, ModelMapper modelMapper) {
        this.reservaService = reservaService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<ReservaResponseDTO> createReserva(@Valid @RequestBody ReservaRequestDTO reservaDTO) {
        var reserva = reservaService.criaReserva(reservaDTO);
        var responseDTO = convertToDTO(reserva);
        responseDTO.setPosicaoFila(reservaService.PosicaoReserva(reserva));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}/user/{userId}").buildAndExpand(reserva.getId()).toUri();
        return ResponseEntity.created(location).body(responseDTO);
    }

    @DeleteMapping("/{id}/user/{userId}")
    public ResponseEntity<Void> cancelarReserva(@PathVariable Long id, @PathVariable Long userId) {
        reservaService.cancelaReserva(id, userId);
        return ResponseEntity.noContent().build();
    }

    private ReservaResponseDTO convertToDTO(Reserva reserva) {
        return modelMapper.map(reserva, ReservaResponseDTO.class);
    }
}
