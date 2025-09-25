package ufpb.project.acervodigital.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufpb.project.acervodigital.DTOs.AtivoDigitalResponseDTO;
import ufpb.project.acervodigital.models.AtivoDigital;
import ufpb.project.acervodigital.models.enums.FormatoAtivo;
import ufpb.project.acervodigital.services.AtivoDigitalService;

@RestController
@RequestMapping("/api/assets")
public class AtivoDigitalController {
    private final ModelMapper modelMapper;
    private final AtivoDigitalService ativoService;

    public AtivoDigitalController(AtivoDigitalService ativoDigitalService, ModelMapper modelMapper) {
        this.ativoService = ativoDigitalService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    @Operation(summary = "Retorna todos os ativos digitais", description = "Retorna uma lista com todos os ativos digitais, ou filtrados por título, autor ou formato")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ativos retornados com sucesso", content = {
                    @Content(
                            schema = @Schema(implementation = AtivoDigitalResponseDTO.class)
                    )
            })
    })
    public ResponseEntity<Page<AtivoDigitalResponseDTO>> findAll(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String autor,
            @RequestParam(required = false) FormatoAtivo formato,
            Pageable pageable
    ) {
        Page<AtivoDigital> ativos = ativoService.findAll(titulo, autor, formato, pageable);
        Page<AtivoDigitalResponseDTO> dtos = ativos.map(ativo -> modelMapper.map(ativo, AtivoDigitalResponseDTO.class));
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna um ativo digital", description = "Retorna um ativo digital especificado pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ativo digital encontrado com sucesso", content = {
                    @Content(
                            schema = @Schema(implementation = AtivoDigitalResponseDTO.class)
                    )
            }),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado o ativo digital")
    })
    public ResponseEntity<AtivoDigitalResponseDTO> findById(@PathVariable Long id) {
        var ativo = ativoService.findById(id);
        return ResponseEntity.ok().body(convertToDTO(ativo));
    }

    private AtivoDigitalResponseDTO convertToDTO(AtivoDigital ativoDigital) {
        return modelMapper.map(ativoDigital, AtivoDigitalResponseDTO.class);
    }
}