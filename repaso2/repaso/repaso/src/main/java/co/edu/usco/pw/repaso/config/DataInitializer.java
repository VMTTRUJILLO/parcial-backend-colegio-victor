package co.edu.usco.pw.repaso.config;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import co.edu.usco.pw.repaso.model.RolEntity;
import co.edu.usco.pw.repaso.model.UserEntity;
import co.edu.usco.pw.repaso.model.TeacherEntity;
import co.edu.usco.pw.repaso.repository.RolRepository;
import co.edu.usco.pw.repaso.repository.TeacherRepository;
import co.edu.usco.pw.repaso.repository.UserRepository;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(
            RolRepository roleRepository,
            UserRepository userRepository,
            TeacherRepository teacherRepository, 
            BCryptPasswordEncoder passwordEncoder) {

        return args -> {

            RolEntity rectorRole = roleRepository.findByName("RECTOR").orElseGet(() -> roleRepository.save(new RolEntity("RECTOR")));
            RolEntity estudianteRole = roleRepository.findByName("ESTUDIANTE").orElseGet(() -> roleRepository.save(new RolEntity("ESTUDIANTE")));
            RolEntity docenteRole = roleRepository.findByName("DOCENTE").orElseGet(() -> roleRepository.save(new RolEntity("DOCENTE")));

            // Crear usuarios
            if (userRepository.findByUsername("rector").isEmpty()) {
                UserEntity rector = new UserEntity();
                rector.setUsername("rector");
                rector.setPassword(passwordEncoder.encode("1234"));
                rector.setRoles(Set.of(rectorRole));
                userRepository.save(rector);
            }

            if (userRepository.findByUsername("estudiante1").isEmpty()) {
                UserEntity estudiante = new UserEntity();
                estudiante.setUsername("estudiante1");
                estudiante.setPassword(passwordEncoder.encode("1234"));
                estudiante.setRoles(Set.of(estudianteRole));
                userRepository.save(estudiante);
            }
            
            if (userRepository.findByUsername("estudiante2").isEmpty()) {
                UserEntity estudiante = new UserEntity();
                estudiante.setUsername("estudiante2");
                estudiante.setPassword(passwordEncoder.encode("1234"));
                estudiante.setRoles(Set.of(estudianteRole));
                userRepository.save(estudiante);
            }

            if (userRepository.findByUsername("estudiante3").isEmpty()) {
                UserEntity estudiante = new UserEntity();
                estudiante.setUsername("estudiante3");
                estudiante.setPassword(passwordEncoder.encode("1234"));
                estudiante.setRoles(Set.of(estudianteRole));
                userRepository.save(estudiante);
            }

            // Crear docentes usuarios y datos de docentes
            if (userRepository.findByUsername("docente1").isEmpty()) {
                UserEntity docenteUser = new UserEntity();
                docenteUser.setUsername("docente1");
                docenteUser.setPassword(passwordEncoder.encode("1234"));
                docenteUser.setRoles(Set.of(docenteRole));
                userRepository.save(docenteUser);

                TeacherEntity docente = new TeacherEntity();
                docente.setNombre("Carlos");
                docente.setApellido("Ramírez");
                docente.setUsuario(docenteUser);
                teacherRepository.save(docente);
            }

            if (userRepository.findByUsername("docente2").isEmpty()) {
                UserEntity docenteUser = new UserEntity();
                docenteUser.setUsername("docente2");
                docenteUser.setPassword(passwordEncoder.encode("1234"));
                docenteUser.setRoles(Set.of(docenteRole));
                userRepository.save(docenteUser);

                TeacherEntity docente = new TeacherEntity();
                docente.setNombre("María");
                docente.setApellido("González");
                docente.setUsuario(docenteUser);
                teacherRepository.save(docente);
            }

            if (userRepository.findByUsername("docente3").isEmpty()) {
                UserEntity docenteUser = new UserEntity();
                docenteUser.setUsername("docente3");
                docenteUser.setPassword(passwordEncoder.encode("1234"));
                docenteUser.setRoles(Set.of(docenteRole));
                userRepository.save(docenteUser);

                TeacherEntity docente = new TeacherEntity();
                docente.setNombre("Andrés");
                docente.setApellido("Fernández");
                docente.setUsuario(docenteUser);
                teacherRepository.save(docente);
            }

        };
    }
}
