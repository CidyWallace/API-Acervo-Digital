package ufpb.project.acervodigital.services;

import org.springframework.data.jpa.domain.Specification;
import org.apache.commons.text.WordUtils;
import ufpb.project.acervodigital.models.AtivoDigital;
import ufpb.project.acervodigital.models.enums.FormatoAtivo;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;

public class AtivoDigitalSpecification {

    public static Specification<AtivoDigital> comFiltros(String titulo, String autor, FormatoAtivo formato) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(titulo != null && !titulo.trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("titulo")), "%" + titulo.toLowerCase().trim() + "%"));
            }

            if(autor != null && !autor.trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("autor"), "%" +  WordUtils.capitalizeFully(autor).trim() + "%"));
            }

            if (formato != null) {
                predicates.add(criteriaBuilder.equal(root.get("formato"), formato));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
