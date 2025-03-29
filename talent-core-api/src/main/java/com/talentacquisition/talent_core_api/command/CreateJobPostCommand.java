package com.talentacquisition.talent_core_api.command;


import com.talentacquisition.talent_core_api.domain.*;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDate;

@Data
@Builder
public class CreateJobPostCommand {

    @TargetAggregateIdentifier
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
}
