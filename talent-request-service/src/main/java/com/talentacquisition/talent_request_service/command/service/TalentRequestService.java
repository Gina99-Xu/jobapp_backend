package com.talentacquisition.talent_request_service.command.service;

import com.talentacquisition.talent_core_api.domain.RequestStatus;
import com.talentacquisition.talent_request_service.command.commands.CreateTalentRequestCommand;
import com.talentacquisition.talent_request_service.command.dto.CreateTalentRequestCommandDTO;
import com.talentacquisition.talent_request_service.command.dto.TalentRequestResponseDTO;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TalentRequestService {

    private final CommandGateway commandGateway;

    public ResponseEntity<Object> createNewTalentRequest(CreateTalentRequestCommandDTO createTalentRequestCommandDTO) {
        CreateTalentRequestCommand createTalentRequestCommand = CreateTalentRequestCommand
                .builder()
                .talentRequestId(UUID.randomUUID().toString())
                .requestStatus(RequestStatus.OPEN)
                .jobDescription(createTalentRequestCommandDTO.getJobDescription())
                .talentRequestTitle(createTalentRequestCommandDTO.getTalentRequestTitle())
                .candidateSkills(createTalentRequestCommandDTO.getCandidateSkills())
                .startDate(createTalentRequestCommandDTO.getStartDate())
                .build();
        
        try {
            commandGateway.sendAndWait(createTalentRequestCommand);
            TalentRequestResponseDTO talentRequestResponseDTO = new TalentRequestResponseDTO();
            BeanUtils.copyProperties(createTalentRequestCommand, talentRequestResponseDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(talentRequestResponseDTO);
        } catch (Exception e) {
            String exceptionMessage = "Error creating new talent request! " + e.getMessage();
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(exceptionMessage);
        }
    }

}
