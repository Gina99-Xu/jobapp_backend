package com.talentacquisition.user_service.client;


import com.talentacquisition.user_service.dto.JobPostQueryResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("career-portal-service")
public interface JobFeignClient {
	@GetMapping("/job-post/{jobPostId}")
	ResponseEntity<JobPostQueryResponseDTO> findJobPostById(@PathVariable String jobPostId);
}
