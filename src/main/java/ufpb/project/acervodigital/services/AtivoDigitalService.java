package ufpb.project.acervodigital.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.apache.commons.text.WordUtils;
import ufpb.project.acervodigital.exception.ItemNotFoundException;
import ufpb.project.acervodigital.models.AtivoDigital;
import ufpb.project.acervodigital.models.enums.FormatoAtivo;
import ufpb.project.acervodigital.repositores.AtivoDigitalRepository;

@Service
public class AtivoDigitalService {
    private final AtivoDigitalRepository ativoRepository;

    public AtivoDigitalService(AtivoDigitalRepository ativoRepository) {
        this.ativoRepository = ativoRepository;
    }

    public Page<AtivoDigital> findAll(String titulo, String autor, FormatoAtivo formato, Pageable pageable) {
        Specification<AtivoDigital> spec = AtivoDigitalSpecification.comFiltros(titulo, autor, formato);

        return ativoRepository.findAll(spec, pageable);
    }

    public AtivoDigital findById(Long id) {
        return ativoRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Ativo digital "+id+" não encontrado"));
    }

    public AtivoDigital save(AtivoDigital ativo) {
        ativo.setAutor(WordUtils.capitalizeFully(ativo.getAutor()));
        return ativoRepository.save(ativo);
    }

    public AtivoDigital update(Long id, AtivoDigital UpAtivo) {
        var ativo = ativoRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Ativo digital "+id+" não encontrado"));

        ativo.setTitulo(UpAtivo.getTitulo());
        ativo.setAutor(UpAtivo.getAutor());
        ativo.setFormato(UpAtivo.getFormato());
        ativo.setTotalLicencas(UpAtivo.getTotalLicencas());
        ativo.setLicencasDisponiveis(UpAtivo.getLicencasDisponiveis());
        return ativoRepository.save(ativo);
    }

    public void delete(Long id) {
        if(!ativoRepository.existsById(id)) {
            throw new ItemNotFoundException("Ativo digital "+id+" não encontrado");
        }
        ativoRepository.deleteById(id);
    }
}
