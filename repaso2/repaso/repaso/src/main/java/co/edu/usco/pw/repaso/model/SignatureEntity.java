package co.edu.usco.pw.repaso.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class SignatureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String nombre;

    @Column(length = 100)
    private String descripcion;

    private Integer salon;

    private String horaInicio;

    private String horaFin;

    @ManyToOne
    @JoinColumn(name = "docente_id")
    private TeacherEntity docente;

}
