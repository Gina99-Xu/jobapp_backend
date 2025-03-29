package com.talentacquisition.talent_fulfillment_service.command.service;

import com.talentacquisition.talent_core_api.domain.RequestStatus;
import com.talentacquisition.talent_fulfillment_service.command.commands.SubmitTalentFulfillmentDecisionCommand;
import com.talentacquisition.talent_fulfillment_service.query.repository.TalentFulfillment;
import com.talentacquisition.talent_fulfillment_service.query.repository.TalentFulfillmentRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class TalentFulfillmentService {

    private final CommandGateway commandGateway;

    @Autowired
    private TalentFulfillmentRepository talentFulfillmentRepository;

    public ResponseEntity<Object> submitTalentFulfillmentDecision(SubmitTalentFulfillmentDecisionCommand submitTalentFulfillmentDecisionCommand) {
        TalentFulfillment talentFulfillment = talentFulfillmentRepository.findById(submitTalentFulfillmentDecisionCommand.getTalentFulfillmentId()).get();

        if (talentFulfillment.getJobPostId() != null) {
            return new ResponseEntity<>("Job post is already exists: " + submitTalentFulfillmentDecisionCommand.getJobPostId(), HttpStatus.BAD_REQUEST);
        }

        if (submitTalentFulfillmentDecisionCommand.getRequestStatus().equals(RequestStatus.APPROVED)) {
            submitTalentFulfillmentDecisionCommand.setJobPostId(UUID.randomUUID().toString());
            commandGateway.sendAndWait(submitTalentFulfillmentDecisionCommand);
            return new ResponseEntity<>(submitTalentFulfillmentDecisionCommand, HttpStatus.OK);
        }
        return new ResponseEntity<>("NOT APPROVED", HttpStatus.BAD_REQUEST);
    }
}
