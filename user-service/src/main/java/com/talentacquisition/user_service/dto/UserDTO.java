package com.talentacquisition.user_service.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
	private Long userId;
	private String firstName;
	private String lastName;
	private int mobileNumber;
	private String userEmail;
	private List<UserResumeAnalysisDTO> userJobAppliedList;

}
