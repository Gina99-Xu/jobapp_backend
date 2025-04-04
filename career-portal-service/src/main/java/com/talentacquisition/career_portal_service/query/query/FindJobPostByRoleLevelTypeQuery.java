package com.talentacquisition.career_portal_service.query.query;


import com.talentacquisition.talent_core_api.domain.RoleLevel;
import com.talentacquisition.talent_core_api.domain.SkillLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindJobPostByRoleLevelTypeQuery {
	private RoleLevel roleLevel;
}
