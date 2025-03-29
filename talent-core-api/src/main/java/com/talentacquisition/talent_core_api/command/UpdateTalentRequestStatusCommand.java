package com.talentacquisition.talent_core_api.command;


import com.talentacquisition.talent_core_api.domain.RequestStatus;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class UpdateTalentRequestStatusCommand {

    @TargetAggregateIdentifier
    private String talentRequestId;
    private RequestStatus requestStatus;
}
