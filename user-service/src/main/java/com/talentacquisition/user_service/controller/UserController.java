package com.talentacquisition.user_service.controller;


import com.talentacquisition.user_service.dto.UserWithJobDetailsResponseDto;
import com.talentacquisition.user_service.entity.UserEntity;
import com.talentacquisition.user_service.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-service")
@AllArgsConstructor
public class UserController {

	@Autowired
	private UserService userService;


	@PostMapping
	public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity userEntity) {
		UserEntity savedUser = userService.saveUser(userEntity);
		return ResponseEntity.ok(savedUser);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserEntity> getUserById(@PathVariable Long userId) {
		UserEntity existUser = userService.getUserById(userId);
		if (existUser != null) {
			return ResponseEntity.ok(existUser);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	public ResponseEntity<List<UserEntity>> getAllUsers() {
		List<UserEntity> users = userService.getAllUsers();
		return ResponseEntity.ok(users);
	}

	@GetMapping("/userDetails")
	public ResponseEntity<UserWithJobDetailsResponseDto> getUserWithJobDetails(@RequestParam("userEmail") String userEmail) {

		UserWithJobDetailsResponseDto userWithJobDetailsResponseDto = userService.findUserWithJobDetails(userEmail);
		return ResponseEntity.ok(userWithJobDetailsResponseDto);
	}


}
