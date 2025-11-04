package co.edu.usco.pw.repaso.services;


import co.edu.usco.pw.repaso.model.SignatureEntity;
import co.edu.usco.pw.repaso.model.TeacherEntity;
import co.edu.usco.pw.repaso.repository.SignatureRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SignatureService {

    private final SignatureRepository signatureRepository;

    public SignatureService(SignatureRepository signatureRepository) {
        this.signatureRepository = signatureRepository;
    }


    public SignatureEntity saveSignature(SignatureEntity signature) {
        return signatureRepository.save(signature);
    }


    public List<SignatureEntity> findByTeacher(TeacherEntity teacher) {
        return signatureRepository.findByDocente(teacher);
    }


    public Optional<SignatureEntity> findById(Long id) {
        return signatureRepository.findById(id);
    }
}
