package co.edu.usco.pw.repaso.repository;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.usco.pw.repaso.model.RolEntity;

public interface RolRepository extends JpaRepository<RolEntity, Long> {
    Optional<RolEntity> findByName(String name);
}