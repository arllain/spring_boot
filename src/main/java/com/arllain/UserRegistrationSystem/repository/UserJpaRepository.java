package com.arllain.UserRegistrationSystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arllain.UserRegistrationSystem.dto.UserDTO;


@Repository
public interface UserJpaRepository extends JpaRepository<UserDTO, Long>{
	
	Optional<UserDTO> findById(Long id);

	UserDTO findByName(String name);

}
