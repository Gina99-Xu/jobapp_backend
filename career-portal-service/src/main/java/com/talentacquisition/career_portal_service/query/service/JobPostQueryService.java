package com.talentacquisition.career_portal_service.query.service;

import com.talentacquisition.career_portal_service.query.dto.JobPostQueryResponseDTO;
import com.talentacquisition.career_portal_service.query.query.FindAllJobPostsQuery;
import com.talentacquisition.career_portal_service.query.query.FindJobPostByJobPostIdQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPostQueryService {

    private final QueryGateway queryGateway;

    public ResponseEntity findJobPostById(String jobPostId) throws Exception {
        FindJobPostByJobPostIdQuery findJobPostByJobPostIdQuery = new FindJobPostByJobPostIdQuery(jobPostId);
        JobPostQueryResponseDTO jobPostQueryResponseDTO = queryGateway.query(findJobPostByJobPostIdQuery, ResponseTypes.instanceOf(JobPostQueryResponseDTO.class)).get();
        return new ResponseEntity(jobPostQueryResponseDTO, HttpStatus.OK);
    }

    public ResponseEntity findAllJobPosts() throws Exception {
        FindAllJobPostsQuery findAllJobPostsQuery = new FindAllJobPostsQuery();
        List<JobPostQueryResponseDTO> jobPostQueryResponseDTOList = queryGateway
                .query(findAllJobPostsQuery, ResponseTypes.multipleInstancesOf(JobPostQueryResponseDTO.class)).get();
        return new ResponseEntity(jobPostQueryResponseDTOList, HttpStatus.OK);
    }

}
