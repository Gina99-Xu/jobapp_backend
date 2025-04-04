package com.talentacquisition.career_portal_service.query.repository;

import com.talentacquisition.talent_core_api.domain.CoreSkill;
import com.talentacquisition.talent_core_api.domain.EmploymentType;
import com.talentacquisition.talent_core_api.domain.RoleLevel;
import com.talentacquisition.talent_core_api.domain.SkillLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.management.relation.Role;
import java.util.List;

public interface JobPostRepository extends JpaRepository<JobPost, String> {

	List<JobPost> findByEmploymentType(EmploymentType employmentType);
	List<JobPost> findByRoleLevel(RoleLevel roleLevel);

	List<JobPost> findByCandidateSkillsCoreSkill(CoreSkill coreSkill);
	List<JobPost> findByCandidateSkillsSkillLevel(SkillLevel skillLevel);
	List<JobPost> findByCandidateSkillsCoreSkillAndCandidateSkillsSkillLevel(
			CoreSkill coreSkill, SkillLevel skillLevel);
}
