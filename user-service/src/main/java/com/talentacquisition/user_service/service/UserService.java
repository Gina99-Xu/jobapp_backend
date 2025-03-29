package com.talentacquisition.user_service.service;


import com.talentacquisition.user_service.client.JobFeignClient;
import com.talentacquisition.user_service.dto.JobPostQueryResponseDTO;
import com.talentacquisition.user_service.dto.UserWithJobDetailsResponseDto;
import com.talentacquisition.user_service.entity.UserEntity;
import com.talentacquisition.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JobFeignClient jobFeignClient;


	public UserEntity saveUser(UserEntity userEntity) {
		return userRepository.save(userEntity);
	}

	public List<UserEntity> getAllUsers() {
		return userRepository.findAll();
	}

	public UserEntity getUserById(Long id) {
		Optional<UserEntity> user = userRepository.findById(id);
		return user.orElse(null);
	}

	public UserEntity findUserByEmail(String userEmail) {
		UserEntity user = userRepository.findUserByUserEmail(userEmail);
		return user;
	}


	public UserWithJobDetailsResponseDto findUserWithJobDetails(String userEmail) {
		UserEntity userEntity = userRepository.findUserByUserEmail(userEmail);

		ResponseEntity<JobPostQueryResponseDTO> jobPostQueryResponseDTO = jobFeignClient.findJobPostById(userEntity.getJobPostId());
		UserWithJobDetailsResponseDto userWithJobDetailsResponseDto = new UserWithJobDetailsResponseDto();

		userWithJobDetailsResponseDto.setJobPostQueryResponseDTO(jobPostQueryResponseDTO.getBody());
		userWithJobDetailsResponseDto.setUserEmail(userEmail);
		userWithJobDetailsResponseDto.setUserId(userEntity.getUserId());
		userWithJobDetailsResponseDto.setFirstName(userEntity.getFirstName());
		userWithJobDetailsResponseDto.setLastName(userEntity.getLastName());
		userWithJobDetailsResponseDto.setMobileNumber(userEntity.getMobileNumber());
		userWithJobDetailsResponseDto.setJobPostId(userEntity.getJobPostId());

		return userWithJobDetailsResponseDto;

	}


}
