package com.talentacquisition.career_portal_service.config;

import com.talentacquisition.talent_core_api.domain.RoleLevel;
import com.talentacquisition.talent_core_api.domain.SkillLevel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;



@Component
public class RoleLevelTypeConverter implements Converter<String, RoleLevel> {
	@Override
	public RoleLevel convert(String source) {
		try{
			return RoleLevel.valueOf(source.toUpperCase());
		}catch(IllegalArgumentException e){
			throw new IllegalArgumentException("Invalid RoleLevel Type" + source);
		}
	}
}
