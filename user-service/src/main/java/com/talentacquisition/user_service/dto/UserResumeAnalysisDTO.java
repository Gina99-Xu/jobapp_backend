package com.talentacquisition.user_service.dto;


import lombok.Data;

@Data
public class UserResumeAnalysisDTO {
	private Long userResumeAnalysisId;
	private String jobPostId;
	private Double experienceScore;
	private Double skillsScore;
	private Double educationScore;
	private Double overallScore;
	private String overallAnalysis;
}
