package com.arllain.UserRegistrationSystem.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arllain.UserRegistrationSystem.Exception.CustomErrorType;
import com.arllain.UserRegistrationSystem.dto.UserDTO;
import com.arllain.UserRegistrationSystem.repository.UserJpaRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/user")
public class UserRegistrationRestController {

	public static final Logger logger = LoggerFactory.getLogger(UserRegistrationRestController.class);

	private UserJpaRepository userJpaRepository;

	@Autowired
	public void setUserJpaRepostory(UserJpaRepository userJpaRepository) {
		this.userJpaRepository = userJpaRepository;
	}

	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> listAllUsers() {
		List<UserDTO> users = userJpaRepository.findAll();
		if (users.isEmpty()) {
			return new ResponseEntity<List<UserDTO>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<UserDTO>>(users, HttpStatus.OK);
	}

	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody final UserDTO user) {

		logger.info("Criando o usuário: {}" + user.getName());
		if (userJpaRepository.findByName(user.getName()) != null) {
			logger.error("Não é possível criar o novo usuário. Um usuáro com o nome {} já existe", user.getName());			
			return new ResponseEntity<UserDTO>(new CustomErrorType(
					"Não é possível criar o novo usuário." + "Um usuário com o nome " + user.getName() + " já existe."),
					HttpStatus.CONFLICT);
		}

		userJpaRepository.save(user);
		return new ResponseEntity<UserDTO>(user, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable("id") final Long id) {

		Optional<UserDTO> optional = userJpaRepository.findById(id);
		if (!optional.isPresent()) {
			return new ResponseEntity<UserDTO>(new CustomErrorType("Usuário com o id " + id + " não encontrado"),
					HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<UserDTO>(optional.get(), HttpStatus.OK);
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> updateUser(@PathVariable("id") final Long id, @RequestBody UserDTO user) {

		Optional<UserDTO> optional = userJpaRepository.findById(id);

		if (!optional.isPresent()) {
			return new ResponseEntity<UserDTO>(
					new CustomErrorType("Não foi possível atualizar. O id " + id + " não foi encontrado."),
					HttpStatus.NOT_FOUND);
		}

		UserDTO currentUser = optional.get();
		currentUser.setName(user.getName());
		currentUser.setAddress(user.getAddress());
		currentUser.setEmail(user.getEmail());
		userJpaRepository.saveAndFlush(currentUser);

		return new ResponseEntity<UserDTO>(currentUser, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<UserDTO> deleteUser(@PathVariable("id") final Long id) {

		Optional<UserDTO> optional = userJpaRepository.findById(id);

		if (!optional.isPresent()) {
			return new ResponseEntity<UserDTO>(
					new CustomErrorType("Não foi possível deletar. O id " + id + " não foi encontrado."),
					HttpStatus.NOT_FOUND);
		}

		userJpaRepository.deleteById(id);

		return new ResponseEntity<UserDTO>(HttpStatus.NO_CONTENT);
	}

}
