package com.talentacquisition.career_portal_service.query.dto;

import com.talentacquisition.talent_core_api.domain.CandidateSkills;
import com.talentacquisition.talent_core_api.domain.EmploymentType;
import com.talentacquisition.talent_core_api.domain.JobDescription;
import com.talentacquisition.talent_core_api.domain.RoleLevel;
import lombok.Data;

@Data
public class JobPostQueryResponseDTO {

	private String jobPostId;
	private String talentRequestTitle;
	private JobDescription jobDescription;
	private CandidateSkills candidateSkills;
	private RoleLevel roleLevel;
	private EmploymentType employmentType;

}
