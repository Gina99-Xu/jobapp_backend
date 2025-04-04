package com.talentacquisition.career_portal_service.query.controller;


import com.talentacquisition.career_portal_service.query.dto.JobPostQueryResponseDTO;
import com.talentacquisition.career_portal_service.query.service.JobPostQueryService;
import com.talentacquisition.talent_core_api.domain.CoreSkill;
import com.talentacquisition.talent_core_api.domain.EmploymentType;
import com.talentacquisition.talent_core_api.domain.RoleLevel;
import com.talentacquisition.talent_core_api.domain.SkillLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/job-post")
public class JobPostQueryController {

	@Autowired
	private JobPostQueryService jobPostQueryService;

	@GetMapping ("/all-jobs")
	public ResponseEntity findAllJobPosts() throws Exception {
		return jobPostQueryService.findAllJobPosts();
	}

	@GetMapping("/{jobPostId}")
	public ResponseEntity<JobPostQueryResponseDTO> findJobPostById(@PathVariable String jobPostId) throws Exception {
		return jobPostQueryService.findJobPostById(jobPostId);
	}

	@GetMapping
	public ResponseEntity<JobPostQueryResponseDTO> findJobPosts(
			@RequestParam(required = false) EmploymentType employmentType,
			@RequestParam(required = false) CoreSkill coreSkill,
			@RequestParam(required = false) RoleLevel roleLevel,
			@RequestParam(required = false) SkillLevel skillLevel) throws Exception {

		if (coreSkill != null && skillLevel != null) {
			return jobPostQueryService.findJobPostByCoreSkillAndSkillLevelType(coreSkill, skillLevel);
		}

		if (employmentType != null) {
			return jobPostQueryService.findJobPostByEmploymentType(employmentType);
		}

		if (coreSkill != null) {
			return jobPostQueryService.findJobPostByCoreSkillType(coreSkill);
		}
		if (roleLevel != null) {
			return jobPostQueryService.findJobPostByRoleLevelType(roleLevel);
		}
		if (skillLevel != null) {
			return jobPostQueryService.findJobPostBySkillLevelType(skillLevel);
		}
		return jobPostQueryService.findAllJobPosts();
	}


}
