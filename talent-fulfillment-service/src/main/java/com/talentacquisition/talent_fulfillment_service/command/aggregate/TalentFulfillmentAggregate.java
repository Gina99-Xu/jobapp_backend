package com.talentacquisition.talent_fulfillment_service.command.aggregate;


import com.talentacquisition.talent_core_api.command.CreateTalentFulfillmentCommand;
import com.talentacquisition.talent_core_api.domain.*;
import com.talentacquisition.talent_core_api.event.TalentFulfillmentCreatedEvent;
import com.talentacquisition.talent_fulfillment_service.command.commands.SubmitTalentFulfillmentDecisionCommand;
import com.talentacquisition.talent_fulfillment_service.command.events.TalentFulfillmentDecisionSubmittedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

@Aggregate
public class TalentFulfillmentAggregate {

    @AggregateIdentifier
    private String talentFulfillmentId;

    private String jobPostId;
    private String talentRequestId;
    private String talentRequestTitle;
    private LocalDate startDate;

    private JobDescription jobDescription;
    private CandidateSkills candidateSkills;
    private RequestStatus requestStatus;

    private RoleLevel roleLevel;
    private EmploymentType employmentType;

    public TalentFulfillmentAggregate() {
    }

    @CommandHandler
    public TalentFulfillmentAggregate(CreateTalentFulfillmentCommand createTalentFulfillmentCommand) {
        TalentFulfillmentCreatedEvent talentFulfillmentCreatedEvent = new TalentFulfillmentCreatedEvent();
        BeanUtils.copyProperties(createTalentFulfillmentCommand, talentFulfillmentCreatedEvent);

        /*IMPORTANT -
        CreateTalentFulfillmentCommand is from shared core-api package!
        once the CreateTalentFulfillmentCommand successfully passed from saga to this aggregate
        then we can update the status to assigned before publishing it  */
        talentFulfillmentCreatedEvent.setRequestStatus(RequestStatus.ASSIGNED);
        AggregateLifecycle.apply(talentFulfillmentCreatedEvent);
    }

    @EventSourcingHandler
    public void on(TalentFulfillmentCreatedEvent talentFulfillmentCreatedEvent) {
        this.talentFulfillmentId = talentFulfillmentCreatedEvent.getTalentFulfillmentId();
        this.talentRequestId = talentFulfillmentCreatedEvent.getTalentRequestId();
        this.talentRequestTitle = talentFulfillmentCreatedEvent.getTalentRequestTitle();
        this.candidateSkills = talentFulfillmentCreatedEvent.getCandidateSkills();
        this.jobDescription = talentFulfillmentCreatedEvent.getJobDescription();
        this.requestStatus = talentFulfillmentCreatedEvent.getRequestStatus();
        this.startDate = talentFulfillmentCreatedEvent.getStartDate();
    }

    @CommandHandler
    public void handle(SubmitTalentFulfillmentDecisionCommand submitTalentFulfillmentDecisionCommand) {
        TalentFulfillmentDecisionSubmittedEvent talentFulfillmentDecisionSubmittedEvent = new TalentFulfillmentDecisionSubmittedEvent();
        BeanUtils.copyProperties(submitTalentFulfillmentDecisionCommand, talentFulfillmentDecisionSubmittedEvent);
        AggregateLifecycle.apply(talentFulfillmentDecisionSubmittedEvent);
    }

    @EventSourcingHandler
    public void on(TalentFulfillmentDecisionSubmittedEvent talentFulfillmentDecisionSubmittedEvent) {
        this.talentFulfillmentId = talentFulfillmentDecisionSubmittedEvent.getTalentFulfillmentId();
        this.talentRequestId = talentFulfillmentDecisionSubmittedEvent.getTalentRequestId();
        this.jobPostId = talentFulfillmentDecisionSubmittedEvent.getJobPostId();

        this.startDate = talentFulfillmentDecisionSubmittedEvent.getStartDate();
        this.talentRequestTitle = talentFulfillmentDecisionSubmittedEvent.getTalentRequestTitle();
        this.requestStatus = talentFulfillmentDecisionSubmittedEvent.getRequestStatus();
        this.jobDescription = talentFulfillmentDecisionSubmittedEvent.getJobDescription();
        this.candidateSkills = talentFulfillmentDecisionSubmittedEvent.getCandidateSkills();
        this.employmentType = talentFulfillmentDecisionSubmittedEvent.getEmploymentType();
        this.roleLevel = talentFulfillmentDecisionSubmittedEvent.getRoleLevel();

    }

}
