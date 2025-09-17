package ufpb.project.acervodigital.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufpb.project.acervodigital.DTOs.EmprestimoRequestDTO;
import ufpb.project.acervodigital.DTOs.EmprestimoResponseDTO;
import ufpb.project.acervodigital.models.Emprestimo;
import ufpb.project.acervodigital.services.EmprestimoService;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class EmprestimoController {

    private final EmprestimoService emprestimoService;
    private final ModelMapper modelMapper;

    public EmprestimoController(EmprestimoService emprestimoService, ModelMapper modelMapper) {
        this.emprestimoService = emprestimoService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<EmprestimoResponseDTO> criarEmprestimo(@RequestBody EmprestimoRequestDTO emprestimoDTO){
        var emprestimo = emprestimoService.criarEmprestimo(emprestimoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(emprestimo));
    }

    @PostMapping("/{loanId}")
    public ResponseEntity<EmprestimoResponseDTO> devolucaoEmprestimo(@PathVariable Long loanId){
        var emprestimo = emprestimoService.devolucaoEmprestimo(loanId);
        return ResponseEntity.status(HttpStatus.OK).body(convertToDTO(emprestimo));
    }

    @GetMapping
    public ResponseEntity<List<EmprestimoResponseDTO>> listarEmprestimos(@RequestParam(required = false) Long userId){
        List<EmprestimoResponseDTO> emprestimos;
        if(userId != null){
            emprestimos = emprestimoService.buscarPorUsuario(userId).stream().map(this::convertToDTO).toList();
        }else{
            emprestimos = emprestimoService.listarEmprestimo().stream().map(this::convertToDTO).toList();
        }

        return ResponseEntity.ok(emprestimos);
    }

    private EmprestimoResponseDTO convertToDTO(Emprestimo emprestimo) {
        return modelMapper.map(emprestimo, EmprestimoResponseDTO.class);
    }
}
