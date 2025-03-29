package com.talentacquisition.user_service.dto;


import com.talentacquisition.talent_core_api.domain.CandidateSkills;
import com.talentacquisition.talent_core_api.domain.EmploymentType;
import com.talentacquisition.talent_core_api.domain.JobDescription;
import com.talentacquisition.talent_core_api.domain.RoleLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobPostQueryResponseDTO {
	private String jobPostId;
	private String talentRequestTitle;
	private JobDescription jobDescription;
	private CandidateSkills candidateSkills;
	private RoleLevel roleLevel;
	private EmploymentType employmentType;
}
