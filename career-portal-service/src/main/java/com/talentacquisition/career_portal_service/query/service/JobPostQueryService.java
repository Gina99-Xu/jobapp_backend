package com.talentacquisition.career_portal_service.query.service;

import com.talentacquisition.career_portal_service.query.dto.JobPostQueryResponseDTO;
import com.talentacquisition.career_portal_service.query.query.*;
import com.talentacquisition.career_portal_service.query.repository.JobPostRepository;
import com.talentacquisition.talent_core_api.domain.CoreSkill;
import com.talentacquisition.talent_core_api.domain.EmploymentType;
import com.talentacquisition.talent_core_api.domain.RoleLevel;
import com.talentacquisition.talent_core_api.domain.SkillLevel;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class JobPostQueryService {

    private JobPostRepository jobPostRepository;
    private final QueryGateway queryGateway;

    public ResponseEntity findJobPostById(String jobPostId) throws Exception {
        FindJobPostByJobPostIdQuery findJobPostByJobPostIdQuery = new FindJobPostByJobPostIdQuery(jobPostId);
        JobPostQueryResponseDTO jobPostQueryResponseDTO = queryGateway.query(findJobPostByJobPostIdQuery, ResponseTypes.instanceOf(JobPostQueryResponseDTO.class)).join();
        return new ResponseEntity(jobPostQueryResponseDTO, HttpStatus.OK);
    }

    public ResponseEntity findAllJobPosts() throws Exception {
        FindAllJobPostsQuery findAllJobPostsQuery = new FindAllJobPostsQuery();
        List<JobPostQueryResponseDTO> jobPostQueryResponseDTOList = queryGateway
                .query(findAllJobPostsQuery, ResponseTypes.multipleInstancesOf(JobPostQueryResponseDTO.class)).get();
        return new ResponseEntity(jobPostQueryResponseDTOList, HttpStatus.OK);
    }

    public ResponseEntity findJobPostByEmploymentType(EmploymentType employmentType) throws ExecutionException, InterruptedException {
        FindJobPostByEmploymentTypeQuery findJobPostByEmploymentTypeQuery = new FindJobPostByEmploymentTypeQuery(employmentType);
        List<JobPostQueryResponseDTO> jobPostQueryResponseDTOList = queryGateway
                .query(findJobPostByEmploymentTypeQuery, ResponseTypes.multipleInstancesOf(JobPostQueryResponseDTO.class)).join();
        return new ResponseEntity(jobPostQueryResponseDTOList, HttpStatus.OK);
    }

    public ResponseEntity findJobPostByCoreSkillType(CoreSkill coreSkill) throws ExecutionException, InterruptedException {
        FindJobPostByCoreSkillTypeQuery findJobPostByCoreSkillTypeQuery = new FindJobPostByCoreSkillTypeQuery(coreSkill);
        List<JobPostQueryResponseDTO> jobPostQueryResponseDTOList = queryGateway
                .query(findJobPostByCoreSkillTypeQuery, ResponseTypes.multipleInstancesOf(JobPostQueryResponseDTO.class)).join();
        return new ResponseEntity(jobPostQueryResponseDTOList, HttpStatus.OK);
    }

    public ResponseEntity findJobPostByRoleLevelType(RoleLevel roleLevel) throws ExecutionException, InterruptedException {
        FindJobPostByRoleLevelTypeQuery findJobPostByRoleLevelTypeQuery = new FindJobPostByRoleLevelTypeQuery(roleLevel);
        List<JobPostQueryResponseDTO> jobPostQueryResponseDTOList = queryGateway
                .query(findJobPostByRoleLevelTypeQuery, ResponseTypes.multipleInstancesOf(JobPostQueryResponseDTO.class)).join();
        return new ResponseEntity(jobPostQueryResponseDTOList, HttpStatus.OK);
    }

    public ResponseEntity findJobPostBySkillLevelType(SkillLevel skillLevel) throws ExecutionException, InterruptedException {
        FindJobPostBySkillLevelTypeQuery findJobPostBySkillLevelTypeQuery = new FindJobPostBySkillLevelTypeQuery(skillLevel);
        List<JobPostQueryResponseDTO> jobPostQueryResponseDTOList = queryGateway
                .query(findJobPostBySkillLevelTypeQuery, ResponseTypes.multipleInstancesOf(JobPostQueryResponseDTO.class)).join();
        return new ResponseEntity(jobPostQueryResponseDTOList, HttpStatus.OK);
    }

    public ResponseEntity findJobPostByCoreSkillAndSkillLevelType(CoreSkill coreSkill, SkillLevel skillLevel) throws ExecutionException, InterruptedException {
        FindJobPostByCoreSkillAndSkillLevelTypeQuery findJobPostByCoreSkillAndSkillLevelTypeQuery = new FindJobPostByCoreSkillAndSkillLevelTypeQuery(coreSkill, skillLevel);
        List<JobPostQueryResponseDTO> jobPostQueryResponseDTOList = queryGateway
                .query(findJobPostByCoreSkillAndSkillLevelTypeQuery, ResponseTypes.multipleInstancesOf(JobPostQueryResponseDTO.class)).join();
        return new ResponseEntity(jobPostQueryResponseDTOList, HttpStatus.OK);
    }

}
