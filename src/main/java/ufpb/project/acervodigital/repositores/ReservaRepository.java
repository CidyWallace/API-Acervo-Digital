package ufpb.project.acervodigital.repositores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ufpb.project.acervodigital.models.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}
