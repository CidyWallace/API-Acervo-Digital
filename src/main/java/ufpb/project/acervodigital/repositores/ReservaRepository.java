package ufpb.project.acervodigital.repositores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ufpb.project.acervodigital.models.Reserva;
import ufpb.project.acervodigital.models.enums.StatusEmprestimo;
import ufpb.project.acervodigital.models.enums.StatusReserva;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    boolean existsByAtivoIdAndUserIdAndStatus(Long id, Long id1, StatusReserva statusReserva);

    List<Reserva> findByUserIdAndStatus(Long userId, StatusReserva statusReserva);

    Long countByAtivoIdAndStatusAndIdLessThan(Long id, StatusReserva statusReserva,  Long idReserva);
}
