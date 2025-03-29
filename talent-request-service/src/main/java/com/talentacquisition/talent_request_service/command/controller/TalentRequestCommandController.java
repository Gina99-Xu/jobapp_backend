package com.talentacquisition.talent_request_service.command.controller;


import com.talentacquisition.talent_request_service.command.dto.CreateTalentRequestCommandDTO;
import com.talentacquisition.talent_request_service.command.service.TalentRequestService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/talent-request")
@RequiredArgsConstructor
public class TalentRequestCommandController {

    private final TalentRequestService talentRequestService;

    @PostMapping
    public ResponseEntity<Object> createNewTalentRequest(@RequestBody CreateTalentRequestCommandDTO createTalentRequestCommandDTO){
        return talentRequestService.createNewTalentRequest(createTalentRequestCommandDTO);
    }
}
