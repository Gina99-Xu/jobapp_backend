package com.talentacquisition.talent_request_service.query.eventhandler;


import com.talentacquisition.talent_request_service.command.events.TalentRequestCreatedEvent;
import com.talentacquisition.talent_request_service.command.events.TalentRequestStatusUpdatedEvent;
import com.talentacquisition.talent_request_service.query.repository.TalentRequest;
import com.talentacquisition.talent_request_service.query.repository.TalentRequestRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TalentRequestEventHandler {

    @Autowired
    private TalentRequestRepository talentRequestRepository;

    @EventHandler
    public void on(TalentRequestCreatedEvent talentRequestCreatedEvent) {
        TalentRequest talentRequest = new TalentRequest();
        BeanUtils.copyProperties(talentRequestCreatedEvent, talentRequest);
        talentRequestRepository.save(talentRequest);
    }

    @EventHandler
    public void on(TalentRequestStatusUpdatedEvent talentRequestStatusUpdatedEvent) {
        TalentRequest talentRequest = talentRequestRepository.findById(talentRequestStatusUpdatedEvent.getTalentRequestId()).get();
        talentRequest.setRequestStatus(talentRequestStatusUpdatedEvent.getRequestStatus());
        talentRequestRepository.save(talentRequest);
    }


}
