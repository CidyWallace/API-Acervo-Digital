package ufpb.project.acervodigital.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufpb.project.acervodigital.DTOs.*;
import ufpb.project.acervodigital.models.Emprestimo;
import ufpb.project.acervodigital.models.Reserva;
import ufpb.project.acervodigital.models.User;
import ufpb.project.acervodigital.services.EmprestimoService;
import ufpb.project.acervodigital.services.ReservaService;
import ufpb.project.acervodigital.services.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UsuarioService usuarioService;
    private final EmprestimoService emprestimoService;
    private final ReservaService reservaService;
    private final ModelMapper modelMapper;

    public UserController(UsuarioService usuarioService, ModelMapper modelMapper, EmprestimoService emprestimoService, ReservaService reservaService) {
        this.usuarioService = usuarioService;
        this.modelMapper = modelMapper;
        this.emprestimoService = emprestimoService;
        this.reservaService = reservaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findUser(@PathVariable Long id) {
        var user = usuarioService.findById(id);
        return ResponseEntity.ok(convertToDTO(user));
    }

    @GetMapping("/loans/{id}")
    public ResponseEntity<List<EmprestimoResponseDTO>> findLoans(@PathVariable Long id) {
        List<EmprestimoResponseDTO> emprestimos = emprestimoService.buscarPorUsuario(id).stream().map(this::convertToEmprestimoDTO).toList();
        return ResponseEntity.ok(emprestimos);
    }

    @GetMapping("/holds/{id}")
    public ResponseEntity<List<ReservaResponseDTO>> findReservas(@PathVariable Long id) {
        List<Reserva> reserva = reservaService.buscarPorUserId(id);
        List<ReservaResponseDTO> reservasDTO = reserva.stream().map(r -> {
            Long posisao = reservaService.PosicaoReserva(r);

            ReservaResponseDTO responseDTO = convertToReservaDTO(r);
            responseDTO.setPosicaoFila(posisao);
            return responseDTO;
        }).toList();
        return ResponseEntity.ok(reservasDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> atualizarUser(@Valid @RequestBody UserRequestDTO userDTO, @PathVariable Long id) {
        var user = usuarioService.atualizarUser(id, convertToEntity(userDTO));
        return ResponseEntity.ok(convertToDTO(user));
    }

    private UserResponseDTO convertToDTO(User user) {
        return modelMapper.map(user, UserResponseDTO.class);
    }

    private User convertToEntity(UserRequestDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    private EmprestimoResponseDTO convertToEmprestimoDTO(Emprestimo emprestimo) {
        return modelMapper.map(emprestimo, EmprestimoResponseDTO.class);
    }

    private ReservaResponseDTO convertToReservaDTO(Reserva reserva) {
        return modelMapper.map(reserva, ReservaResponseDTO.class);
    }

}
