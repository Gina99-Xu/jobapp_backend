package com.talentacquisition.talent_fulfillment_service.query.controller;


import com.talentacquisition.talent_fulfillment_service.query.service.TalentFulfillmentQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/talent-fulfillment")
public class TalentFulfillmentQueryController {

    @Autowired
    private TalentFulfillmentQueryService talentFulfillmentQueryService;

    @GetMapping
    public ResponseEntity<Object> findAllTalentFulfillment() {
        return talentFulfillmentQueryService.findAllTalentFulfillment();
    }

    @GetMapping("/{talentFulfillmentId}")
    public ResponseEntity<Object> findTalentFulfillmentByID(@PathVariable String talentFulfillmentId) {
        return talentFulfillmentQueryService.findTalentFulfillmentByID(talentFulfillmentId);
    }

}
