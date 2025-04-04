package com.talentacquisition.career_portal_service.config;

import com.talentacquisition.talent_core_api.domain.CoreSkill;
import com.talentacquisition.talent_core_api.domain.EmploymentType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CoreSkillTypeConverter implements Converter<String, CoreSkill> {
	@Override
	public CoreSkill convert(String source) {
		try{
			return CoreSkill.valueOf(source.toUpperCase());
		}catch(IllegalArgumentException e){
			throw new IllegalArgumentException("Invalid CoreSkill Type" + source);
		}
	}
}
