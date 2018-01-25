package com.arllain.UserRegistrationSystem.rest;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arllain.UserRegistrationSystem.Exception.CustomErrorType;
import com.arllain.UserRegistrationSystem.dto.UsersDTO;
import com.arllain.UserRegistrationSystem.repository.UserJpaRepository;


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
	public ResponseEntity<List<UsersDTO>> listAllUsers() {
		logger.info("Consultando todos os usuarios");
		List<UsersDTO> users = userJpaRepository.findAll();
		if (users.isEmpty()) {
			return new ResponseEntity<List<UsersDTO>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<UsersDTO>>(users, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UsersDTO> getUserById(@PathVariable("id") final Long id) {
		logger.info("Consultando usuario com o id {}", id);
		UsersDTO user = userJpaRepository.findById(id);
		if (user == null) {
			return new ResponseEntity<UsersDTO>(new CustomErrorType("Usuário com o id " + id + " não encontrado"),
					HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<UsersDTO>(user, HttpStatus.OK);
	}
	
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsersDTO> createUser(@Valid @RequestBody final UsersDTO user) {

		logger.info("Criando o usuário: {}" + user.getName());
		if (userJpaRepository.findByName(user.getName()) != null) {
			logger.error("Não é possível alterar o usuário. Um usuáro com o nome {} já existe", user.getName());
			return new ResponseEntity<UsersDTO>(new CustomErrorType(
					"Não é possível alterar usuário." + "Um usuário com o nome " + user.getName() + " já existe."),
					HttpStatus.CONFLICT);
		}

		userJpaRepository.save(user);
		return new ResponseEntity<UsersDTO>(user, HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsersDTO> updateUser(@PathVariable("id") final Long id, @RequestBody UsersDTO user) {
		logger.info("Atualizando o usuario com o id {}", id);
		UsersDTO currentUser = userJpaRepository.findById(id);

		if (currentUser == null) {
			logger.error("Não foi possível atualizar. O usuario com o id {} nao encontrado.", id);
			return new ResponseEntity<UsersDTO>(
					new CustomErrorType("Não foi possível atualizar. O id " + id + " não foi encontrado."),
					HttpStatus.NOT_FOUND);
		}

		currentUser.setName(user.getName());
		currentUser.setAddress(user.getAddress());
		currentUser.setEmail(user.getEmail());
		userJpaRepository.saveAndFlush(currentUser);

		return new ResponseEntity<UsersDTO>(currentUser, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<UsersDTO> deleteUser(@PathVariable("id") final Long id) {
		logger.info("Deletando o usuario com o id {}", id);
		UsersDTO user = userJpaRepository.findById(id);

		if (user == null) {
			logger.error("Não foi possível deletar. Usuario com id {} nao encontrado.", id);
			return new ResponseEntity<UsersDTO>(
					new CustomErrorType("Não foi possível deletar. O id " + id + " não foi encontrado."),
					HttpStatus.NOT_FOUND);
		}
		userJpaRepository.delete(id);
		return new ResponseEntity<UsersDTO>(new CustomErrorType("Deleted User with id " + id + "."), HttpStatus.NO_CONTENT);
	}

}
