package com.talentacquisition.talent_fulfillment_service.query.events;


import com.talentacquisition.talent_core_api.event.TalentFulfillmentCreatedEvent;
import com.talentacquisition.talent_fulfillment_service.command.events.TalentFulfillmentDecisionSubmittedEvent;
import com.talentacquisition.talent_fulfillment_service.query.repository.TalentFulfillment;
import com.talentacquisition.talent_fulfillment_service.query.repository.TalentFulfillmentRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TalentFulfillmentEventHandler {

    @Autowired
    private TalentFulfillmentRepository talentFulfillmentRepository;

    @EventHandler
    public void on(TalentFulfillmentCreatedEvent talentFulfillmentCreatedEvent) {
        TalentFulfillment talentFulfillment = new TalentFulfillment();
        BeanUtils.copyProperties(talentFulfillmentCreatedEvent, talentFulfillment);
        talentFulfillmentRepository.save(talentFulfillment);
    }

    @EventHandler
    public void on(TalentFulfillmentDecisionSubmittedEvent talentFulfillmentDecisionSubmittedEvent) {
        TalentFulfillment talentFulfillment = talentFulfillmentRepository.findById(talentFulfillmentDecisionSubmittedEvent.getTalentFulfillmentId()).get();
        BeanUtils.copyProperties(talentFulfillmentDecisionSubmittedEvent, talentFulfillment);
        talentFulfillmentRepository.save(talentFulfillment);

    }


}
