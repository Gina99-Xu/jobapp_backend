package com.talentacquisition.career_portal_service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConfigurationUtil {
	public class WebConfig implements WebMvcConfigurer {
		@Override
		public void addFormatters(FormatterRegistry registry){
			registry.addConverter(new EmploymentTypeConverter());
			registry.addConverter(new SkillLevelTypeConverter());
			registry.addConverter(new CoreSkillTypeConverter());
			registry.addConverter(new RoleLevelTypeConverter());
		}
	}
}

