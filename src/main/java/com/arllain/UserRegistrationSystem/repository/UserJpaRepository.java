package com.arllain.UserRegistrationSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arllain.UserRegistrationSystem.dto.UsersDTO;


@Repository
public interface UserJpaRepository extends JpaRepository<UsersDTO, Long>{
	
	UsersDTO findById(Long id);

	UsersDTO findByName(String name);

}
