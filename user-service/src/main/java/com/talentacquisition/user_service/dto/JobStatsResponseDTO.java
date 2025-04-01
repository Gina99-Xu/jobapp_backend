package com.talentacquisition.user_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobStatsResponseDTO {

	private int totalJobApplicants;
	private Double avgExperienceScore;
	private Double avgSkillsScore;
	private Double avgEducationScore;
	private Double avgOverallScore;

}
