package com.talentacquisition.career_portal_service.query.controller;


import com.talentacquisition.career_portal_service.query.dto.JobPostQueryResponseDTO;
import com.talentacquisition.career_portal_service.query.service.JobPostQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job-post")
public class JobPostQueryController {

	@Autowired
	private JobPostQueryService jobPostQueryService;

	@GetMapping
	public ResponseEntity findAllJobPosts() throws Exception {
		return jobPostQueryService.findAllJobPosts();
	}

	@GetMapping("/{jobPostId}")
	public ResponseEntity<JobPostQueryResponseDTO> findJobPostById(@PathVariable String jobPostId) throws Exception {
		return jobPostQueryService.findJobPostById(jobPostId);
	}

}
