package com.talentacquisition.talent_request_service.command.events;

import com.talentacquisition.talent_core_api.domain.RequestStatus;
import lombok.Data;


@Data
public class TalentRequestStatusUpdatedEvent {
    private String talentRequestId;
    private RequestStatus requestStatus;
}
