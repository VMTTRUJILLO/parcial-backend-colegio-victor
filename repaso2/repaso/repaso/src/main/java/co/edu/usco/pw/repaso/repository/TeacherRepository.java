package co.edu.usco.pw.repaso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.usco.pw.repaso.model.TeacherEntity;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {
    
	Optional<TeacherEntity> findByUsuarioUsername(String username);

}
