package ufpb.project.acervodigital.repositores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ufpb.project.acervodigital.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
