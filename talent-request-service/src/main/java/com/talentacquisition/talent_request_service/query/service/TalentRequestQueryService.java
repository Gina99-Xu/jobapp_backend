package com.talentacquisition.talent_request_service.query.service;


import com.talentacquisition.talent_request_service.command.dto.TalentRequestResponseDTO;
import com.talentacquisition.talent_request_service.query.FindAllTalentRequestsQuery;
import com.talentacquisition.talent_request_service.query.FindTalentRequestByIdQuery;
import com.talentacquisition.talent_request_service.query.dto.TalentRequestQueryResponseDTO;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TalentRequestQueryService {

    private final QueryGateway queryGateway;

    public ResponseEntity<Object> findAllTalentRequests(){
        FindAllTalentRequestsQuery findAllTalentRequestsQuery = new FindAllTalentRequestsQuery();
        List<TalentRequestQueryResponseDTO> talentRequestResponseDTOList
                = queryGateway.query(findAllTalentRequestsQuery, ResponseTypes.multipleInstancesOf(TalentRequestQueryResponseDTO.class)).join();
        return new ResponseEntity<>(talentRequestResponseDTOList, HttpStatus.OK);
    }


    public ResponseEntity<Object> findByTalentRequestId(String talentRequestId)  throws Exception{
        FindTalentRequestByIdQuery findTalentRequestByIdQuery = new FindTalentRequestByIdQuery(talentRequestId);
        TalentRequestQueryResponseDTO talentRequestQueryResponseDTO = queryGateway
                .query(findTalentRequestByIdQuery, ResponseTypes.instanceOf(TalentRequestQueryResponseDTO.class)).join();
        return new ResponseEntity<>(talentRequestQueryResponseDTO, HttpStatus.OK);
    }
}
