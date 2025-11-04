package co.edu.usco.pw.repaso.controller;

import co.edu.usco.pw.repaso.model.SignatureEntity;
import co.edu.usco.pw.repaso.model.TeacherEntity;
import co.edu.usco.pw.repaso.repository.SignatureRepository;
import co.edu.usco.pw.repaso.repository.TeacherRepository;
import co.edu.usco.pw.repaso.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class TeacherController {

    @Autowired
    private SignatureRepository signatureRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private UserRepository userRepository;

    // Método para obtener el docente actual basándose en la sesión
    private TeacherEntity obtenerDocenteActual(Authentication authentication) {
        String username = authentication.getName();
        return teacherRepository.findByUsuarioUsername(username)
                .orElseThrow(() -> new RuntimeException("Docente no encontrado"));
    }
    @GetMapping("/asignaturas_docente")
    public String verAsignaturasDocente(Model model, Authentication authentication) {
        // Asegúrate de tener este método para obtener al docente actual
        TeacherEntity docente = obtenerDocenteActual(authentication);

        // Aquí recuperas las asignaturas del docente
        List<SignatureEntity> asignaturas = signatureRepository.findByDocente(docente);

        // IMPORTANTE: esto lo necesita el HTML para mostrar la lista
        model.addAttribute("asignaturas", asignaturas);

        return "asignaturas_docente"; // este debe ser el nombre del HTML
    }


    @GetMapping("/edit_horario/{id}")
    public String editarHorario(@PathVariable Long id, Model model) {
        SignatureEntity signature = signatureRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));
        model.addAttribute("signature", signature);
        return "edit_horario"; // Nombre correcto del HTML
    }

    @PostMapping("/edit_horario/{id}")
    public String actualizarHorario(@PathVariable Long id, @ModelAttribute SignatureEntity signatureActualizada) {
        SignatureEntity signature = signatureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));
        signature.setHoraInicio(signatureActualizada.getHoraInicio());
        signature.setHoraFin(signatureActualizada.getHoraFin());
        signatureRepository.save(signature);
        return "redirect:/asignaturas_docente"; 
    }

}


