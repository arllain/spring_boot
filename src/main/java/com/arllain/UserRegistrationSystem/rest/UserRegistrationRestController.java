package com.arllain.UserRegistrationSystem.rest;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	public static final Logger logger = 
			LoggerFactory.getLogger(UserRegistrationRestController.class);

	private UserJpaRepository userJpaRepository;
	
	@Autowired
	public void setUserJpaRepostory(UserJpaRepository userJpaRepository) {
		this.userJpaRepository = userJpaRepository;
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> listAllUsers()	{
		List<UserDTO> users = userJpaRepository.findAll();
		return new ResponseEntity<List<UserDTO>>(users, HttpStatus.OK);
	}
	
	@PostMapping(value = "/", consumes =MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> createUser(@RequestBody final UserDTO user){
		
		userJpaRepository.save(user);
		return new ResponseEntity<UserDTO>(user, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional> getUserById(@PathVariable("id") final Long id){
		Optional<UserDTO> user = userJpaRepository.findById(id);
		return new ResponseEntity<Optional>(user, HttpStatus.OK);
	}
	
	
	@PutMapping(value = "/{id}", consumes =MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> updateUser(
			@PathVariable("id") final Long id, @RequestBody UserDTO user){
		
//		Object currentUser = userJpaRepository.findById(id);
//		
//		((UserDTO) currentUser).setName(user.getName());
//		((UserDTO) currentUser).setAddress(user.getAddress());
//		((UserDTO) currentUser).setEmail(user.getEmail());
//		
		//userJpaRepository.saveAndFlush(currentUser);
		return null;//new ResponseEntity<UserDTO>(currentUser, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Optional> deleteUser(@PathVariable("id") final Long id) {
	        userJpaRepository.deleteById(id);
	        return new ResponseEntity<Optional>(HttpStatus.NO_CONTENT);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
