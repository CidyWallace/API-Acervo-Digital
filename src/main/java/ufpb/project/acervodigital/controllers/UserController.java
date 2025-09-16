package ufpb.project.acervodigital.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufpb.project.acervodigital.DTOs.*;
import ufpb.project.acervodigital.models.User;
import ufpb.project.acervodigital.models.enums.StatusUsuario;
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

    @GetMapping("/loans")
    public ResponseEntity<List<EmprestimoDTO>> findLoans() {
        return ResponseEntity.ok(List.of(new EmprestimoDTO()));
    }

    @GetMapping
    public ResponseEntity<List<ReservaDTO>> findReservas() {
        return ResponseEntity.ok(List.of(new ReservaDTO()));
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> criarUser(@RequestBody UserRequestDTO userDTO) {
        var user = usuarioService.criarUsuario(convertToEntity(userDTO));
        return ResponseEntity.ok(convertToDTO(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> atualizarUser(@RequestBody UserRequestDTO userDTO, @PathVariable Long id) {
        var user = usuarioService.atualizarUser(id, convertToEntity(userDTO));
        return ResponseEntity.ok(convertToDTO(user));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDTO> atualizaStatus(@PathVariable Long id, @RequestBody UserStatusRequestDTO status) {
        var user = usuarioService.atualizarStatus(id, convertToEnum(status));
        return ResponseEntity.ok(convertToDTO(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        usuarioService.deletaUser(id);
        return ResponseEntity.noContent().build();
    }

    private UserResponseDTO convertToDTO(User user) {
        return modelMapper.map(user, UserResponseDTO.class);
    }

    private User convertToEntity(UserRequestDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    private StatusUsuario convertToEnum(UserStatusRequestDTO status) {
        return modelMapper.map(status, StatusUsuario.class);
    }

}
