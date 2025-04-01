package com.talentacquisition.user_service.controller;


import com.talentacquisition.user_service.dto.JobStatsResponseDTO;
import com.talentacquisition.user_service.service.UserResumeAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-resume-analysis")
public class UserResumeAnalysisController {


	@Autowired
	private UserResumeAnalysisService userResumeAnalysisService;


	@GetMapping("/job-stats")
	public ResponseEntity<JobStatsResponseDTO> findJobStatsByJobPostId (@RequestParam String jobPostId) {
		return ResponseEntity.status(HttpStatus.OK).body(userResumeAnalysisService.findJobStats(jobPostId));
	}
}
