package com.talentacquisition.user_service.repository;

import com.talentacquisition.user_service.entity.UserEntity;
import com.talentacquisition.user_service.entity.UserResumeAnalysisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserResumeAnalysisEntityRepository extends JpaRepository<UserResumeAnalysisEntity, Long> {

	List<UserResumeAnalysisEntity> findByJobPostId(String jobPostId);

	@Query("SELECT AVG(u.experienceScore) FROM UserResumeAnalysisEntity u WHERE u.jobPostId = :jobPostId")
	Double findAvgExperienceScoreGroupedByJobPostId(String jobPostId);

	@Query("SELECT AVG(u.skillsScore) FROM UserResumeAnalysisEntity u WHERE u.jobPostId = :jobPostId")
	Double findAvgSkillsScoreGroupByJobPostId(String jobPostId);

	@Query("SELECT AVG(u.educationScore) FROM UserResumeAnalysisEntity u WHERE u.jobPostId = :jobPostId")
	Double findAvgEducationScoreGroupByJobPostId(String jobPostId);

	@Query("SELECT AVG(u.overallScore) FROM UserResumeAnalysisEntity u WHERE u.jobPostId = :jobPostId")
	Double findAvgOverallScoreGroupByJobPostId(String jobPostId);
}
