package com.talentacquisition.user_service.dto;


import lombok.Data;

@Data
public class UserResumeRequestDTO {
	private String firstName;
	private String lastName;
	private int mobileNumber;
	private String userEmail;
	private String jobPostId;
}
