package com.talentacquisition.career_portal_service.config;

import com.talentacquisition.talent_core_api.domain.EmploymentType;
import com.talentacquisition.talent_core_api.domain.SkillLevel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SkillLevelTypeConverter implements Converter<String, SkillLevel> {
	@Override
	public SkillLevel convert(String source) {
		try{
			return SkillLevel.valueOf(source.toUpperCase());
		}catch(IllegalArgumentException e){
			throw new IllegalArgumentException("Invalid SkillLevel Type" + source);
		}
	}
}
