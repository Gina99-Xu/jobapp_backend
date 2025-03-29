package com.talentacquisition.user_service.dto;


import lombok.Data;

@Data
public class UserWithJobDetailsResponseDto {

	private Long userId;
	private String firstName;
	private String lastName;
	private int mobileNumber;
	private String userEmail;
	private String jobPostId;

	private JobPostQueryResponseDTO jobPostQueryResponseDTO;

}
