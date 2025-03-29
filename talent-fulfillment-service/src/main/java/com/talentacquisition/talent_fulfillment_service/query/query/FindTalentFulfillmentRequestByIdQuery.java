package com.talentacquisition.talent_fulfillment_service.query.query;

import lombok.Data;

@Data
public class FindTalentFulfillmentRequestByIdQuery {
    private final String talentFulfillmentRequestId;

    public FindTalentFulfillmentRequestByIdQuery(String talentFulfillmentRequestId) {
        this.talentFulfillmentRequestId = talentFulfillmentRequestId;
    }
}
