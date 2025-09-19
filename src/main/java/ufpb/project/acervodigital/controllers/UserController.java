package ufpb.project.acervodigital.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<UserResponseDTO> findUser(Authentication authentication) {
        var user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(convertToDTO(usuarioService.findById(user.getId())));
    }

    @GetMapping("/loans")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<EmprestimoResponseDTO>> findLoans(Authentication authentication) {
        var user = (User) authentication.getPrincipal();
        List<EmprestimoResponseDTO> emprestimos = emprestimoService.buscarPorUsuario(user.getId()).stream().map(this::convertToEmprestimoDTO).toList();
        return ResponseEntity.ok(emprestimos);
    }

    @GetMapping("/holds")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<ReservaResponseDTO>> findReservas(Authentication authentication) {
        var user = (User) authentication.getPrincipal();
        List<Reserva> reserva = reservaService.buscarPorUserId(user.getId());
        List<ReservaResponseDTO> reservasDTO = reserva.stream().map(r -> {
            Long posisao = reservaService.PosicaoReserva(r);

            ReservaResponseDTO responseDTO = convertToReservaDTO(r);
            responseDTO.setPosicaoFila(posisao);
            return responseDTO;
        }).toList();
        return ResponseEntity.ok(reservasDTO);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<UserResponseDTO> atualizarUser(@Valid @RequestBody UserRequestDTO userDTO, Authentication authentication) {
        var user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(convertToDTO(usuarioService.atualizarUser(user.getId(), convertToEntity(userDTO))));
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
