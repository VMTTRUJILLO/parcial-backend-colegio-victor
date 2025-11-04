package co.edu.usco.pw.repaso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.ui.Model;
import co.edu.usco.pw.repaso.repository.SignatureRepository;

@Controller
@RequestMapping("/")
public class AsignaturasController {

    @Autowired
    private SignatureRepository signatureRepository;

    @GetMapping("/student")
    public String listarAsignaturas(Model model) {
        model.addAttribute("asignaturas", signatureRepository.findAll());
        return "student"; // tu HTML
    }
    
}
