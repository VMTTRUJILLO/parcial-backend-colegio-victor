package co.edu.usco.pw.repaso.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/inicio")
    public String showInicioPage() {
        return "inicio";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/admin")
    public String showAdminPage() {
        return "admin"; // Para rector que administre
    }



  
    @GetMapping("/403")
    public String error403() {
        return "403error"; 
    }

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
