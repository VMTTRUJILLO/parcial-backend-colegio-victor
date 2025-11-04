package co.edu.usco.pw.repaso.controller;

import co.edu.usco.pw.repaso.model.SignatureEntity;
import co.edu.usco.pw.repaso.repository.SignatureRepository;
import co.edu.usco.pw.repaso.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/") // <--- ESTO ES MUY IMPORTANTE
public class RectorController {

    @Autowired
    private SignatureRepository signatureRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping("")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("signature", new SignatureEntity());
        model.addAttribute("docentes", teacherRepository.findAll());
        return "registro"; 
    }

    @PostMapping("/registro")
    public String guardarAsignatura(@ModelAttribute SignatureEntity signature) {
        signatureRepository.save(signature);
        return "redirect:/registro";
    }


    @GetMapping("/registro")
    public String mostrarFormularioRegistroOtraVez(Model model) {
        model.addAttribute("signature", new SignatureEntity());
        model.addAttribute("docentes", teacherRepository.findAll());
        return "registro";
    }
    


    @GetMapping("/view")
    public String listarAsignaturas(Model model) {
        model.addAttribute("asignaturas", signatureRepository.findAll());
        return "view"; // tu HTML
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarAsignatura(@PathVariable Long id) {
        signatureRepository.deleteById(id);
        return "redirect:/view"; // Después de eliminar, vuelve a la lista
    }

    
}
