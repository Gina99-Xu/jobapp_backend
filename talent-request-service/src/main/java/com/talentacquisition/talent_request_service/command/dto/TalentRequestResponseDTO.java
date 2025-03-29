package com.talentacquisition.talent_request_service.command.dto;


import com.talentacquisition.talent_core_api.domain.RequestStatus;
import lombok.Data;

@Data
public class TalentRequestResponseDTO {

    private String talentRequestId;
    private String talentRequestTitle;
    private RequestStatus requestStatus;
}
