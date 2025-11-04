package co.edu.usco.pw.repaso.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class TeacherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    
    private String apellido;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity usuario;
}
