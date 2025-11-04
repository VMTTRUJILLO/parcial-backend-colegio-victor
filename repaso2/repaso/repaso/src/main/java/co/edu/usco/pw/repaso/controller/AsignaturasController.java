package co.edu.usco.pw.repaso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.ui.Model;
import co.edu.usco.pw.repaso.repository.SignatureRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Controller
@RequestMapping("/")
@Tag(name = "Asignaturas", description = "Controlador encargado de manejar las asignaturas vistas por el estudiante")
public class AsignaturasController {

    @Autowired
    private SignatureRepository signatureRepository;
    @Operation(
            summary = "Listar asignaturas del estudiante",
            description = "Obtiene todas las asignaturas disponibles en el sistema y las muestra en la vista del estudiante."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Asignaturas cargadas correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno al obtener las asignaturas")
    })

    @GetMapping("/student")
    public String listarAsignaturas(Model model) {
        model.addAttribute("asignaturas", signatureRepository.findAll());
        return "student"; // tu HTML
    }
    
}
