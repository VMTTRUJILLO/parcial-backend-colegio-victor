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
// Importaciones Swagger
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Controller
@RequestMapping("/")
@Tag(name = "Docente", description = "Controlador para la gestión de asignaturas y horarios de los docentes")
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
    @Operation(
            summary = "Ver asignaturas del docente",
            description = "Obtiene la lista de asignaturas asociadas al docente actualmente autenticado."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de asignaturas mostrada correctamente"),
            @ApiResponse(responseCode = "401", description = "El docente no está autenticado"),
            @ApiResponse(responseCode = "404", description = "Docente no encontrado en el sistema")
    })
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
    @Operation(
            summary = "Editar horario de asignatura",
            description = "Permite al docente acceder a la vista para modificar el horario de una asignatura específica."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vista de edición cargada correctamente"),
            @ApiResponse(responseCode = "404", description = "Asignatura no encontrada")
    })


    @GetMapping("/edit_horario/{id}")
    public String editarHorario(@PathVariable Long id, Model model) {
        SignatureEntity signature = signatureRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));
        model.addAttribute("signature", signature);
        return "edit_horario"; // Nombre correcto del HTML
    }
    @Operation(
            summary = "Actualizar horario de asignatura",
            description = "Guarda los nuevos valores de hora de inicio y hora de fin de una asignatura del docente."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "302", description = "Horario actualizado y redirigido a la lista de asignaturas"),
            @ApiResponse(responseCode = "404", description = "Asignatura no encontrada")
    })

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


