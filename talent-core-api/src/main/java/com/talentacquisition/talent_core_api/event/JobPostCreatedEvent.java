package com.talentacquisition.talent_core_api.event;


import com.talentacquisition.talent_core_api.domain.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class JobPostCreatedEvent {

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
