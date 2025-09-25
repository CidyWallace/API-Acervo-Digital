package ufpb.project.acervodigital.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Admin - Gerencia usuarios e ativos", description = "Endpoints para administradores gerenciarem os usuários e ativos do sistema.")
@SecurityRequirement(name = "bearerAuth")
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
    @Operation(summary = "Cria um novo ativo digital", description = "Cria um novo ativo digital e retorna as informações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ativo digital criado com sucesso", content = {
                    @Content(
                            schema = @Schema(implementation = AtivoDigitalResponseDTO.class)
                    )
            }),
            @ApiResponse(responseCode = "500", description = "Foram enviados dados inválidos", content = {
                    @Content(
                            schema = @Schema(implementation = AtivoDigitalRequestDTO.class)
                    )
            })
    })
    public ResponseEntity<AtivoDigitalResponseDTO> create(@Valid @RequestBody AtivoDigitalRequestDTO ativoDigitalRequestDTO) {
        var ativo = ativoDigitalService.save(convertToAtivoEntity(ativoDigitalRequestDTO));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(ativo.getId()).toUri();
        return ResponseEntity.created(location).body(convertToAtivoDTO(ativo));
    }

    @PutMapping("/assets/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Atualiza os dados de um ativo digital", description = "Atualiza os dados de um ativo digital informado pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ativo digital atualizado com sucesso",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = AtivoDigitalResponseDTO.class)
                            )
                    }),
            @ApiResponse(responseCode = "404", description = "Ativo digital não encontrado"),
            @ApiResponse(responseCode = "500", description = "Foram enviados dados inválidos", content = {
                    @Content(
                            schema = @Schema(implementation = AtivoDigitalRequestDTO.class)
                    )
            }),
    })
    public ResponseEntity<AtivoDigitalResponseDTO> update(@Valid @RequestBody AtivoDigitalRequestDTO ativoDigitalRequestDTO, @PathVariable Long id) {
        var ativo = ativoDigitalService.update(id, convertToAtivoEntity(ativoDigitalRequestDTO));
        return ResponseEntity.ok(convertToAtivoDTO(ativo));
    }

    @PatchMapping("/assets/{id}/licenses")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Atualiza o número de licenças de um ativo digital", description = "Atualiza o número de um ativo digital informado pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ativo digital criado com sucesso", content = {
                    @Content(
                            schema = @Schema(implementation = AtivoDigitalResponseDTO.class)
                    )
            }),
            @ApiResponse(responseCode = "404", description = "Ativo digital não encontrado"),
            @ApiResponse(responseCode = "500", description = "Foram enviados dados inválidos", content = {
                    @Content(
                            schema = @Schema(implementation = AtivoUpdateLicencesDTO.class)
                    )
            }),
    })
    public ResponseEntity<AtivoDigitalResponseDTO> updateLicences(@Valid @RequestBody AtivoUpdateLicencesDTO ativoUpdateLicencesDTO, @PathVariable Long id) {
        var ativo = ativoDigitalService.updateLicencas(id, ativoUpdateLicencesDTO.getLicence());
        return ResponseEntity.ok(convertToAtivoDTO(ativo));
    }

    @DeleteMapping("/assets/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Deleta um ativo digital", description = "Deleta o ativo digital informado pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ativo digital deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ativo digital não encontrado")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ativoDigitalService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Cria um novo usuário", description = "Cria um novo usuário e retorna as informações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso", content = {
                    @Content(
                            schema = @Schema(implementation = UserResponseDTO.class)
                    )
            }),
            @ApiResponse(responseCode = "500", description = "Foram enviados dados inválidos", content = {
                    @Content(
                            schema = @Schema(implementation = UserRequestDTO.class)
                    )
            }),
    })
    public ResponseEntity<UserResponseDTO> criarUser(@Valid @RequestBody UserRequestDTO userDTO) {
        var user = usuarioService.criarUsuario(convertToUserEntity(userDTO));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(location).body(convertToUserDTO(user));
    }

    @GetMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "retorna um usuário", description = "Retorna os dados de um usuário especificado pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<UserResponseDTO> findUser(@PathVariable Long id) {
        var user = usuarioService.findById(id);
        return ResponseEntity.ok(convertToUserDTO(user));
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Retorna todos os usuários", description = "Retorna uma lista com todos os usuários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso", content = {
                    @Content(
                            schema = @Schema(implementation = UserResponseDTO.class)
                    )
            }),
    })
    public ResponseEntity<List<UserResponseDTO>> listAllUsers() {
        return ResponseEntity.ok(usuarioService.listarUsuarios().stream().map(this::convertToUserDTO).toList());
    }

    @PatchMapping("/users/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Atualiza o status de um usuário", description = "Atualiza o status do usuário especificado pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Foram enviados dados inválidos", content = {
                    @Content(
                            schema = @Schema(implementation = UserStatusRequestDTO.class)
                    )
            }),
    })
    public ResponseEntity<UserResponseDTO> atualizaStatus(@PathVariable Long id, @RequestBody UserStatusRequestDTO status) {
        var user = usuarioService.atualizarStatus(id, status.getStatus().toUpperCase());
        return ResponseEntity.ok(convertToUserDTO(user));
    }

    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Deleta um usuário", description = "Deleta um usuário especificado pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        usuarioService.deletaUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/loans")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Retorna todos os empréstimos", description = "Retorna os empréstimos de todos os usuários ou de um usuário especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada cam sucesso", content = {
                    @Content(
                            schema = @Schema(implementation = EmprestimoResponseDTO.class)
                    )
            }),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
    })
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
