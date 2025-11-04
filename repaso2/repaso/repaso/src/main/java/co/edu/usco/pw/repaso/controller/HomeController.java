package co.edu.usco.pw.repaso.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Controller
@Tag(name = "Home", description = "Controlador principal de navegación del sistema (inicio, login, redirecciones y errores)")
public class HomeController {
    @Operation(
            summary = "Página de inicio",
            description = "Muestra la página inicial pública del sistema."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Página de inicio mostrada correctamente")
    })

    @GetMapping("/inicio")
    public String showInicioPage() {
        return "inicio";
    }
    @Operation(
            summary = "Página de inicio de sesión",
            description = "Carga la vista del formulario de login para ingresar al sistema."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Página de login mostrada correctamente"),
            @ApiResponse(responseCode = "401", description = "Credenciales inválidas")
    })

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @Operation(
            summary = "Panel del rector",
            description = "Carga la vista del panel administrativo para usuarios con rol RECTOR."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Página del rector mostrada correctamente"),
            @ApiResponse(responseCode = "403", description = "Acceso denegado: el usuario no tiene permisos de RECTOR")
    })


    @GetMapping("/admin")
    public String showAdminPage() {
        return "admin"; // Para rector que administre
    }

    @Operation(
            summary = "Página de error 403",
            description = "Se muestra cuando el usuario intenta acceder a una página sin los permisos requeridos."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "403", description = "Error de acceso denegado mostrado correctamente")
    })

  
    @GetMapping("/403")
    public String error403() {
        return "403error"; 
    }
    @Operation(
            summary = "Redirección después del login",
            description = "Redirige al usuario según su rol: RECTOR, DOCENTE o ESTUDIANTE."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "302", description = "Redirección exitosa según el rol del usuario"),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado")
    })

    @GetMapping("/default")
    public String defaultAfterLogin(Authentication authentication) {
        if (authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_RECTOR"))) {
            return "redirect:/admin"; // o el flujo que prefieras
        } else if (authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_DOCENTE"))) {
            return "redirect:/asignaturas_docente";
        } else if (authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ESTUDIANTE"))) {
            return "redirect:/student";
        }  else if (authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_DOCENTE"))) {
            return "redirect:/edit_horario/{id}";
        } 
        return "redirect:/inicio"; // Si no tiene rol, a la página pública
    }
}
