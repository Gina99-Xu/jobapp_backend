package com.talentacquisition.talent_request_service.query.queryhandler;

import com.talentacquisition.talent_request_service.query.FindAllTalentRequestsQuery;
import com.talentacquisition.talent_request_service.query.FindTalentRequestByIdQuery;
import com.talentacquisition.talent_request_service.query.dto.TalentRequestQueryResponseDTO;
import com.talentacquisition.talent_request_service.query.repository.TalentRequest;
import com.talentacquisition.talent_request_service.query.repository.TalentRequestRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TalentRequestQueryHandler {

	private final TalentRequestRepository talentRequestRepository;

	@QueryHandler
	public List<TalentRequestQueryResponseDTO> findAllTalentRequests(FindAllTalentRequestsQuery findAllTalentRequestsQuery) {
		List<TalentRequestQueryResponseDTO> talentRequestQueryResponseDTOList = new ArrayList<>();

		List<TalentRequest> talentRequestList = talentRequestRepository.findAll();
		for (TalentRequest talentRequest : talentRequestList) {
			TalentRequestQueryResponseDTO talentRequestQueryResponseDTO = new TalentRequestQueryResponseDTO();
			BeanUtils.copyProperties(talentRequest, talentRequestQueryResponseDTO);
			talentRequestQueryResponseDTOList.add(talentRequestQueryResponseDTO);
		}
		return talentRequestQueryResponseDTOList;
	}

	@QueryHandler
	public TalentRequestQueryResponseDTO findByTalentRequestId(FindTalentRequestByIdQuery findTalentRequestByIdQuery) {

		TalentRequestQueryResponseDTO talentRequestQueryResponseDTO = new TalentRequestQueryResponseDTO();
		TalentRequest talentRequest = talentRequestRepository.findById(findTalentRequestByIdQuery.getTalentRequestId()).get();
		BeanUtils.copyProperties(talentRequest, talentRequestQueryResponseDTO);
		return talentRequestQueryResponseDTO;
	}
}
