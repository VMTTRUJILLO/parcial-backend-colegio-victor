package co.edu.usco.pw.repaso.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.usco.pw.repaso.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	Optional<UserEntity> findByUsername(String username);
}