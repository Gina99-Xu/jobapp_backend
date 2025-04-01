package com.talentacquisition.user_service.service;


import com.talentacquisition.user_service.dto.JobStatsResponseDTO;
import com.talentacquisition.user_service.entity.UserEntity;
import com.talentacquisition.user_service.entity.UserResumeAnalysisEntity;
import com.talentacquisition.user_service.repository.UserResumeAnalysisEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UserResumeAnalysisService {

	@Autowired
	private UserResumeAnalysisEntityRepository userResumeAnalysisEntityRepository;

	public JobStatsResponseDTO findJobStats(String jobPostId) {

		int totalNumberOfApplicants = userResumeAnalysisEntityRepository.findByJobPostId(jobPostId)
				.stream()
				.map(UserResumeAnalysisEntity::getUserEntity)
				.toList().size();

		Double avgExperienceScore = userResumeAnalysisEntityRepository.findAvgExperienceScoreGroupedByJobPostId(jobPostId);
		Double avgSkillsScore = userResumeAnalysisEntityRepository.findAvgSkillsScoreGroupByJobPostId(jobPostId);
		Double avgEducationScore = userResumeAnalysisEntityRepository.findAvgEducationScoreGroupByJobPostId(jobPostId);
		Double avgOverallScore = userResumeAnalysisEntityRepository.findAvgOverallScoreGroupByJobPostId(jobPostId);

		avgExperienceScore = avgExperienceScore != null ? avgExperienceScore : 0.0;
		avgSkillsScore = avgSkillsScore != null ? avgSkillsScore : 0.0;
		avgEducationScore = avgEducationScore != null ? avgEducationScore : 0.0;
		avgOverallScore = avgOverallScore != null ? avgOverallScore : 0.0;

		return new JobStatsResponseDTO(totalNumberOfApplicants, avgExperienceScore, avgSkillsScore, avgEducationScore, avgOverallScore);
	}

}


