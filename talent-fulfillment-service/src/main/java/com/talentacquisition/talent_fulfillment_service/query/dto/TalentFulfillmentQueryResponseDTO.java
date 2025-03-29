package com.talentacquisition.talent_fulfillment_service.query.dto;

import com.talentacquisition.talent_core_api.domain.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TalentFulfillmentQueryResponseDTO {

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


}
