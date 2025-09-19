package ufpb.project.acervodigital.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ufpb.project.acervodigital.DTOs.*;
import ufpb.project.acervodigital.models.AtivoDigital;
import ufpb.project.acervodigital.models.Emprestimo;
import ufpb.project.acervodigital.models.User;
import ufpb.project.acervodigital.services.AtivoDigitalService;
import ufpb.project.acervodigital.services.EmprestimoService;
import ufpb.project.acervodigital.services.UsuarioService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final UsuarioService usuarioService;
    private final AtivoDigitalService ativoDigitalService;
    private final EmprestimoService emprestimoService;
    private final ModelMapper modelMapper;

    public AdminController(UsuarioService usuarioService, AtivoDigitalService ativoDigitalService, EmprestimoService emprestimoService, ModelMapper modelMapper) {
        this.usuarioService = usuarioService;
        this.ativoDigitalService = ativoDigitalService;
        this.emprestimoService = emprestimoService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/assets")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AtivoDigitalResponseDTO> create(@Valid @RequestBody AtivoDigitalRequestDTO ativoDigitalRequestDTO) {
        var ativo = ativoDigitalService.save(convertToAtivoEntity(ativoDigitalRequestDTO));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(ativo.getId()).toUri();
        return ResponseEntity.created(location).body(convertToAtivoDTO(ativo));
    }

    @PutMapping("/assets/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AtivoDigitalResponseDTO> update(@Valid @RequestBody AtivoDigitalRequestDTO ativoDigitalRequestDTO, @PathVariable Long id) {
        var ativo = ativoDigitalService.update(id, convertToAtivoEntity(ativoDigitalRequestDTO));
        return ResponseEntity.ok(convertToAtivoDTO(ativo));
    }

    @PatchMapping("/assets/{id}/licenses")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AtivoDigitalResponseDTO> updateLicences(@Valid @RequestBody AtivoUpdateLicencesDTO ativoUpdateLicencesDTO, @PathVariable Long id) {
        var ativo = ativoDigitalService.updateLicencas(id, ativoUpdateLicencesDTO.getLicence());
        return ResponseEntity.ok(convertToAtivoDTO(ativo));
    }

    @DeleteMapping("/assets/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ativoDigitalService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDTO> criarUser(@Valid @RequestBody UserRequestDTO userDTO) {
        var user = usuarioService.criarUsuario(convertToUserEntity(userDTO));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(location).body(convertToUserDTO(user));
    }

    @GetMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDTO> findUser(@PathVariable Long id) {
        var user = usuarioService.findById(id);
        return ResponseEntity.ok(convertToUserDTO(user));
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponseDTO>> listAllUsers() {
        return ResponseEntity.ok(usuarioService.listarUsuarios().stream().map(this::convertToUserDTO).toList());
    }

    @PatchMapping("/users/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDTO> atualizaStatus(@PathVariable Long id, @RequestBody UserStatusRequestDTO status) {
        var user = usuarioService.atualizarStatus(id, status.getStatus().toUpperCase());
        return ResponseEntity.ok(convertToUserDTO(user));
    }

    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        usuarioService.deletaUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/loans")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<EmprestimoResponseDTO>> listarEmprestimos(@RequestParam(required = false) Long userId){
        List<EmprestimoResponseDTO> emprestimos;
        if(userId != null){
            emprestimos = emprestimoService.buscarPorUsuario(userId).stream().map(this::convertToEmprestimoDTO).toList();
        }else{
            emprestimos = emprestimoService.listarEmprestimo().stream().map(this::convertToEmprestimoDTO).toList();
        }

        return ResponseEntity.ok(emprestimos);
    }

    private EmprestimoResponseDTO convertToEmprestimoDTO(Emprestimo emprestimo) {
        return modelMapper.map(emprestimo, EmprestimoResponseDTO.class);
    }

    private UserResponseDTO convertToUserDTO(User user) {
        return modelMapper.map(user, UserResponseDTO.class);
    }

    private User convertToUserEntity(@Valid UserRequestDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    private AtivoDigital convertToAtivoEntity(AtivoDigitalRequestDTO ativoDigitalRequestDTO) {
        return modelMapper.map(ativoDigitalRequestDTO, AtivoDigital.class);
    }

    private AtivoDigitalResponseDTO convertToAtivoDTO(AtivoDigital ativo){
        return modelMapper.map(ativo, AtivoDigitalResponseDTO.class);
    }

}
