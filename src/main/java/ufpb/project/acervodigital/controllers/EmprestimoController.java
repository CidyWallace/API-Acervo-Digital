package ufpb.project.acervodigital.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ufpb.project.acervodigital.DTOs.EmprestimoResponseDTO;
import ufpb.project.acervodigital.models.Emprestimo;
import ufpb.project.acervodigital.models.User;
import ufpb.project.acervodigital.services.EmprestimoService;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
@Tag(name = "Empreśtimo", description = "Endpoints para criar, devolver e listar empréstimos de um usuário logado")
@SecurityRequirement(name = "bearerAuth")
public class EmprestimoController {

    private final EmprestimoService emprestimoService;
    private final ModelMapper modelMapper;

    public EmprestimoController(EmprestimoService emprestimoService, ModelMapper modelMapper) {
        this.emprestimoService = emprestimoService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/{id_ativo}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @Operation(summary = "Realiza um empréstimo de um ativo digital", description = "Cria um novo empréstimo de um ativo digital para um usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empréstimo criado com sucessido",
                content = {
                        @Content(
                            schema = @Schema(implementation = EmprestimoResponseDTO.class)
                        )
            }),
            @ApiResponse(responseCode = "404", description = "O Ativo digital não foi encontrado")
    })
    public ResponseEntity<EmprestimoResponseDTO> criarEmprestimo(Authentication authentication, @PathVariable Long id_ativo) {
        var user = (User) authentication.getPrincipal();
        var emprestimo = emprestimoService.criarEmprestimo(id_ativo, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(emprestimo));
    }

    @PostMapping("/{loanId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @Operation(summary = "Devolução de um ativo digital", description = "Faz a devolução de um ativo digital pelo id especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login bem sucessido", content = {
                    @Content(
                            schema = @Schema(implementation = EmprestimoResponseDTO.class)
                    )
            }),
            @ApiResponse(responseCode = "404", description = "O Empréstimo não foi encontrado")
    })
    public ResponseEntity<EmprestimoResponseDTO> devolucaoEmprestimo(@PathVariable Long loanId){
        var emprestimo = emprestimoService.devolucaoEmprestimo(loanId);
        return ResponseEntity.status(HttpStatus.OK).body(convertToDTO(emprestimo));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @Operation(summary = "Retorna todos os empréstimos", description = "Retorna uma lista com todos os empréstimos realizados pelo usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empréstimos retornados com sucesso", content = {
                @Content(
                        schema = @Schema(implementation = EmprestimoResponseDTO.class)
                )
            }),
    })
    public ResponseEntity<List<EmprestimoResponseDTO>> listarEmprestimos(Authentication authentication){
        var user = (User) authentication.getPrincipal();
        List<EmprestimoResponseDTO> emprestimos = emprestimoService.buscarPorUsuario(user.getId()).stream().map(this::convertToDTO).toList();

        return ResponseEntity.ok(emprestimos);
    }

    private EmprestimoResponseDTO convertToDTO(Emprestimo emprestimo) {
        return modelMapper.map(emprestimo, EmprestimoResponseDTO.class);
    }
}
