package com.talentacquisition.user_service.utilities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OllamaRequest {
	private String model;
	private String prompt;
}
