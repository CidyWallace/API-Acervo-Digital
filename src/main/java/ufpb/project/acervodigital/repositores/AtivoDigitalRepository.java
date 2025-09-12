package ufpb.project.acervodigital.repositores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ufpb.project.acervodigital.models.AtivoDigital;

@Repository
public interface AtivoDigitalRepository extends JpaRepository<AtivoDigital, Long> {
}
