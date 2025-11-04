package co.edu.usco.pw.repaso.controller;

import co.edu.usco.pw.repaso.model.SignatureEntity;
import co.edu.usco.pw.repaso.repository.SignatureRepository;
import co.edu.usco.pw.repaso.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
// Importaciones de Swagger
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Controller
@RequestMapping("/") // <--- ESTO ES MUY IMPORTANTE
@Tag(name = "Rector", description = "Controlador para la gestión de asignaturas (uso exclusivo del RECTOR)")
public class RectorController {

    @Autowired
    private SignatureRepository signatureRepository;

    @Autowired
    private TeacherRepository teacherRepository;
    @Operation(
            summary = "Mostrar formulario de registro de asignatura",
            description = "Carga la vista del formulario para crear una nueva asignatura y lista los docentes disponibles."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Formulario cargado correctamente")
    })

    @GetMapping("")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("signature", new SignatureEntity());
        model.addAttribute("docentes", teacherRepository.findAll());
        return "registro"; 
    }
    @Operation(
            summary = "Registrar nueva asignatura",
            description = "Guarda una nueva asignatura en la base de datos."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "302", description = "Asignatura registrada correctamente, redirige al formulario de registro"),
            @ApiResponse(responseCode = "400", description = "Error en los datos enviados")
    })

    @PostMapping("/registro")
    public String guardarAsignatura(@ModelAttribute SignatureEntity signature) {
        signatureRepository.save(signature);
        return "redirect:/registro";
    }
    @Operation(
            summary = "Mostrar formulario de registro nuevamente",
            description = "Permite volver a visualizar el formulario de creación de asignaturas."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Formulario mostrado correctamente")
    })


    @GetMapping("/registro")
    public String mostrarFormularioRegistroOtraVez(Model model) {
        model.addAttribute("signature", new SignatureEntity());
        model.addAttribute("docentes", teacherRepository.findAll());
        return "registro";
    }
    @Operation(
            summary = "Listar asignaturas registradas",
            description = "Muestra todas las asignaturas registradas junto con su información asociada."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de asignaturas mostrada correctamente")
    })
    


    @GetMapping("/view")
    public String listarAsignaturas(Model model) {
        model.addAttribute("asignaturas", signatureRepository.findAll());
        return "view"; // tu HTML
    }
    @Operation(
            summary = "Eliminar una asignatura",
            description = "Elimina una asignatura del sistema según su identificador (ID)."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "302", description = "Asignatura eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "Asignatura no encontrada")
    })
    
    @GetMapping("/eliminar/{id}")
    public String eliminarAsignatura(@PathVariable Long id) {
        signatureRepository.deleteById(id);
        return "redirect:/view"; // Después de eliminar, vuelve a la lista
    }

    
}
