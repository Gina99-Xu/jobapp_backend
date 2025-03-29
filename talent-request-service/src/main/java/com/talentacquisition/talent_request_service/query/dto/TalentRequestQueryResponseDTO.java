package com.talentacquisition.talent_request_service.query.dto;


import com.talentacquisition.talent_core_api.domain.CandidateSkills;
import com.talentacquisition.talent_core_api.domain.JobDescription;
import com.talentacquisition.talent_core_api.domain.RequestStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TalentRequestQueryResponseDTO {

    private String talentRequestId;
    private String talentRequestTitle;

    private JobDescription jobDescription;
    private CandidateSkills candidateSkills;

    private RequestStatus requestStatus;
    private LocalDate startDate;
}
