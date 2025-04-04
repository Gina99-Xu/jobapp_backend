package com.talentacquisition.career_portal_service.config;

import com.talentacquisition.talent_core_api.domain.EmploymentType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EmploymentTypeConverter implements Converter<String, EmploymentType> {
	@Override
	public EmploymentType convert(String source) {
		try{
			return EmploymentType.valueOf(source.toUpperCase());
		}catch(IllegalArgumentException e){
			throw new IllegalArgumentException("Invalid Employment Type" + source);
		}
	}
}
