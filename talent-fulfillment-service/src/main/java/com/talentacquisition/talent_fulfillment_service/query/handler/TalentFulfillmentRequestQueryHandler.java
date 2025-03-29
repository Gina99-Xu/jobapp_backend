package com.talentacquisition.talent_fulfillment_service.query.handler;


import com.talentacquisition.talent_fulfillment_service.query.dto.TalentFulfillmentQueryResponseDTO;
import com.talentacquisition.talent_fulfillment_service.query.query.FindAllTalentFulfillmentRequestQuery;
import com.talentacquisition.talent_fulfillment_service.query.query.FindTalentFulfillmentRequestByIdQuery;
import com.talentacquisition.talent_fulfillment_service.query.repository.TalentFulfillment;
import com.talentacquisition.talent_fulfillment_service.query.repository.TalentFulfillmentRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TalentFulfillmentRequestQueryHandler {

    private final TalentFulfillmentRepository talentFulfillmentRepository;


    @QueryHandler
    public List<TalentFulfillmentQueryResponseDTO> findAllTalentFulfillmentRequests(FindAllTalentFulfillmentRequestQuery findAllTalentFulfillmentRequestQuery) {
        List<TalentFulfillmentQueryResponseDTO> talentFulfillmentQueryResponseDTOList = new ArrayList<>();
        List<TalentFulfillment> talentFulfillments = talentFulfillmentRepository.findAll();

        for (TalentFulfillment talentFulfillment : talentFulfillments) {
            TalentFulfillmentQueryResponseDTO talentFulfillmentQueryResponseDTO = new TalentFulfillmentQueryResponseDTO();
            BeanUtils.copyProperties(talentFulfillment, talentFulfillmentQueryResponseDTO);
            talentFulfillmentQueryResponseDTOList.add(talentFulfillmentQueryResponseDTO);
        }
        return talentFulfillmentQueryResponseDTOList;
    }

    @QueryHandler
    public TalentFulfillmentQueryResponseDTO findByFulfillmentId(FindTalentFulfillmentRequestByIdQuery findTalentFulfillmentRequestByIdQuery) {
        TalentFulfillmentQueryResponseDTO talentFulfillmentQueryResponseDTO = new TalentFulfillmentQueryResponseDTO();
        TalentFulfillment talentFulfillment = talentFulfillmentRepository.findById(findTalentFulfillmentRequestByIdQuery.getTalentFulfillmentRequestId()).get();
        BeanUtils.copyProperties(talentFulfillment, talentFulfillmentQueryResponseDTO);
        return talentFulfillmentQueryResponseDTO;
    }


}
