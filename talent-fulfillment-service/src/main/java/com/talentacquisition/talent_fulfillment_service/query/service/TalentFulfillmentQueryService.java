package com.talentacquisition.talent_fulfillment_service.query.service;


import com.talentacquisition.talent_fulfillment_service.query.dto.TalentFulfillmentQueryResponseDTO;
import com.talentacquisition.talent_fulfillment_service.query.query.FindAllTalentFulfillmentRequestQuery;
import com.talentacquisition.talent_fulfillment_service.query.query.FindTalentFulfillmentRequestByIdQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TalentFulfillmentQueryService {

    @Autowired
    private final QueryGateway queryGateway;

    public ResponseEntity<Object> findAllTalentFulfillment() {
        FindAllTalentFulfillmentRequestQuery findAllTalentFulfillmentRequestQuery = new FindAllTalentFulfillmentRequestQuery();
        List<TalentFulfillmentQueryResponseDTO> talentFulfillmentQueryResponseDTOList =
                queryGateway.query(findAllTalentFulfillmentRequestQuery, ResponseTypes.multipleInstancesOf(TalentFulfillmentQueryResponseDTO.class
                )).join();
        return new ResponseEntity<>(talentFulfillmentQueryResponseDTOList, HttpStatus.OK);
    }

    public ResponseEntity<Object> findTalentFulfillmentByID(String talentFulfillmentId) {

        FindTalentFulfillmentRequestByIdQuery findTalentFulfillmentRequestByID = new FindTalentFulfillmentRequestByIdQuery(talentFulfillmentId);
        TalentFulfillmentQueryResponseDTO talentFulfillmentQueryResponseDTO = queryGateway
                .query(findTalentFulfillmentRequestByID, ResponseTypes.instanceOf(TalentFulfillmentQueryResponseDTO.class)).join();

        return new ResponseEntity<>(talentFulfillmentQueryResponseDTO, HttpStatus.OK);

    }
}
