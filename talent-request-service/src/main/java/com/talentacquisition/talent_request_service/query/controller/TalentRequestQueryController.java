package com.talentacquisition.talent_request_service.query.controller;

import com.talentacquisition.talent_request_service.query.service.TalentRequestQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("talent-request")
@RequiredArgsConstructor
public class TalentRequestQueryController {

	private final TalentRequestQueryService talentRequestQueryService;

	@GetMapping
	public ResponseEntity<Object> findAllTalentRequests() {
		return talentRequestQueryService.findAllTalentRequests();
	}

	@GetMapping("/{talentRequestId}")
	public ResponseEntity<Object> findByTalentRequestId(@PathVariable String talentRequestId) throws Exception {
		return talentRequestQueryService.findByTalentRequestId(talentRequestId);
	}

}
