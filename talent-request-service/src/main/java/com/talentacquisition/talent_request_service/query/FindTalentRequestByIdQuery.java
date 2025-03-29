package com.talentacquisition.talent_request_service.query;


import lombok.Data;

@Data
public class FindTalentRequestByIdQuery {

    private String talentRequestId;

    public FindTalentRequestByIdQuery(String talentRequestId){
        this.talentRequestId = talentRequestId;
    }
}
