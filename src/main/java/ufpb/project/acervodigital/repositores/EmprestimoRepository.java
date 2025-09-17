package ufpb.project.acervodigital.repositores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ufpb.project.acervodigital.models.Emprestimo;
import ufpb.project.acervodigital.models.enums.StatusEmprestimo;

import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    boolean existsByAtivoIdAndUserIdAndStatus(Long id, Long id1, StatusEmprestimo statusEmprestimo);

    List<Emprestimo> findByUserId(Long userId);
}
