package com.talentacquisition.talent_fulfillment_service.command.controller;


import com.talentacquisition.talent_fulfillment_service.command.commands.SubmitTalentFulfillmentDecisionCommand;
import com.talentacquisition.talent_fulfillment_service.command.service.TalentFulfillmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/talent-fulfillment")
public class TalentFulfillmentController {

    @Autowired
    private TalentFulfillmentService talentFulfillmentService;


    @PostMapping("/job-post")
    public ResponseEntity<Object> createTalentFulfillment(@RequestBody SubmitTalentFulfillmentDecisionCommand submitTalentFulfillmentDecisionCommand) {
        return talentFulfillmentService.submitTalentFulfillmentDecision(submitTalentFulfillmentDecisionCommand);
    }
}
