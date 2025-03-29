package com.talentacquisition.talent_request_service.saga;


import com.talentacquisition.talent_core_api.command.CreateTalentFulfillmentCommand;
import com.talentacquisition.talent_core_api.command.UpdateTalentRequestStatusCommand;
import com.talentacquisition.talent_core_api.event.TalentFulfillmentCreatedEvent;
import com.talentacquisition.talent_request_service.command.events.TalentRequestCreatedEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Saga
public class TalentRequestSaga {

    private static final String TALENT_REQUEST_ID = "talentRequestId";

    @Autowired
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = TALENT_REQUEST_ID)
    public void handle(TalentRequestCreatedEvent talentRequestCreatedEvent) {
        CreateTalentFulfillmentCommand createTalentFulfillmentCommand =
                CreateTalentFulfillmentCommand.builder()
                        .talentFulfillmentId(UUID.randomUUID().toString())
                        .talentRequestId(talentRequestCreatedEvent.getTalentRequestId())
                        .talentRequestTitle(talentRequestCreatedEvent.getTalentRequestTitle())
                        .jobDescription(talentRequestCreatedEvent.getJobDescription())
                        .candidateSkills(talentRequestCreatedEvent.getCandidateSkills())
                        .requestStatus(talentRequestCreatedEvent.getRequestStatus())
                        .startDate(talentRequestCreatedEvent.getStartDate())
                        .build();
        commandGateway.send(createTalentFulfillmentCommand);
    }

    @EndSaga
    @SagaEventHandler(associationProperty = TALENT_REQUEST_ID)
    public void handle(TalentFulfillmentCreatedEvent talentFulfillmentCreatedEvent) {
        UpdateTalentRequestStatusCommand updateTalentRequestStatusCommand =
                UpdateTalentRequestStatusCommand.builder()
                        .talentRequestId((talentFulfillmentCreatedEvent.getTalentRequestId()))
                        .requestStatus(talentFulfillmentCreatedEvent.getRequestStatus())
                        .build();
        commandGateway.send(updateTalentRequestStatusCommand);


    }
}
