package co.edu.usco.pw.repaso.repository;

import co.edu.usco.pw.repaso.model.SignatureEntity;
import co.edu.usco.pw.repaso.model.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SignatureRepository extends JpaRepository<SignatureEntity, Long> {
    List<SignatureEntity> findByDocente(TeacherEntity docente);
}
