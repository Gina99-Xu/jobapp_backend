package com.talentacquisition.talent_request_service.command.dto;


import com.talentacquisition.talent_core_api.domain.CandidateSkills;
import com.talentacquisition.talent_core_api.domain.JobDescription;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CreateTalentRequestCommandDTO {
    private String talentRequestTitle;
    private JobDescription jobDescription;
    private CandidateSkills candidateSkills;
    private LocalDate startDate;
}
