package com.talentacquisition.career_portal_service.command.aggregate;

import com.talentacquisition.talent_core_api.command.CreateJobPostCommand;
import com.talentacquisition.talent_core_api.domain.*;
import com.talentacquisition.talent_core_api.event.JobPostCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

@Aggregate
public class JobPostAggregate {

    @AggregateIdentifier
    private String jobPostId;
    private String talentRequestId;
    private String talentFulfillmentId;

    private String talentRequestTitle;
    private LocalDate startDate;
    private JobDescription jobDescription;
    private CandidateSkills candidateSkills;
    private RequestStatus requestStatus;
    private RoleLevel roleLevel;
    private EmploymentType employmentType;

    public JobPostAggregate() {
    }

    @CommandHandler
    public JobPostAggregate(CreateJobPostCommand createJobPostCommand) {
        JobPostCreatedEvent jobPostCreatedEvent = new JobPostCreatedEvent();
        BeanUtils.copyProperties(createJobPostCommand, jobPostCreatedEvent);
        AggregateLifecycle.apply(jobPostCreatedEvent);
    }

    @EventSourcingHandler
    public void on(JobPostCreatedEvent jobPostCreatedEvent) {
        this.jobPostId = jobPostCreatedEvent.getJobPostId();
        this.talentRequestId = jobPostCreatedEvent.getTalentRequestId();
        this.talentFulfillmentId = jobPostCreatedEvent.getTalentFulfillmentId();
        this.talentRequestTitle = jobPostCreatedEvent.getTalentRequestTitle();
        this.requestStatus = jobPostCreatedEvent.getRequestStatus();
        this.candidateSkills = jobPostCreatedEvent.getCandidateSkills();
        this.jobDescription = jobPostCreatedEvent.getJobDescription();
        this.roleLevel = jobPostCreatedEvent.getRoleLevel();
        this.startDate = jobPostCreatedEvent.getStartDate();
        this.employmentType = jobPostCreatedEvent.getEmploymentType();
    }

}
