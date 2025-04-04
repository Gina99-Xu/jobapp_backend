package com.talentacquisition.career_portal_service.query.query;


import com.talentacquisition.talent_core_api.domain.CoreSkill;
import com.talentacquisition.talent_core_api.domain.EmploymentType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindJobPostByCoreSkillTypeQuery {
	private CoreSkill coreSkill;
}
