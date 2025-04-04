package com.talentacquisition.career_portal_service.query.repository;

import com.talentacquisition.talent_core_api.domain.CoreSkill;
import com.talentacquisition.talent_core_api.domain.EmploymentType;
import com.talentacquisition.talent_core_api.domain.RoleLevel;
import com.talentacquisition.talent_core_api.domain.SkillLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.management.relation.Role;
import java.util.List;

public interface JobPostRepository extends JpaRepository<JobPost, String> {

	@Query("SELECT jp FROM JobPost jp WHERE jp.jobDescription.qualifications LIKE %:keyword% OR jp.jobDescription.responsibilities LIKE %:keyword%")
	List<JobPost> searchByKeyword(@Param("keyword") String keyword);
	List<JobPost> findByEmploymentType(EmploymentType employmentType);
	List<JobPost> findByRoleLevel(RoleLevel roleLevel);

	List<JobPost> findByCandidateSkillsCoreSkill(CoreSkill coreSkill);
	List<JobPost> findByCandidateSkillsSkillLevel(SkillLevel skillLevel);
	List<JobPost> findByCandidateSkillsCoreSkillAndCandidateSkillsSkillLevel(
			CoreSkill coreSkill, SkillLevel skillLevel);
}
