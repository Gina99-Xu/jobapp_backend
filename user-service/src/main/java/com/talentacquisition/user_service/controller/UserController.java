package com.talentacquisition.user_service.controller;

import com.talentacquisition.user_service.dto.UserDTO;
import com.talentacquisition.user_service.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/user-service")
@AllArgsConstructor
public class UserController {

	@Autowired
	private final UserService userService;

	@PostMapping("/save-user-and-analyze-resume")
	public ResponseEntity<UserDTO> saveUserAndAnalyzeResume(
			@RequestPart("userData") String userData,
			@RequestPart("resumePdf")MultipartFile resumePdf) throws IOException {
		try {
			UserDTO savedUserDTO = userService.saveUserAndResumeAnalysis(userData, resumePdf);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedUserDTO);
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping(value = "/resume-analysis", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<UserDTO> findUserResumeByUserEmail(@RequestParam String userEmail) {
		return ResponseEntity.ok(userService.findUserResumeByEmail(userEmail));
	}
}
