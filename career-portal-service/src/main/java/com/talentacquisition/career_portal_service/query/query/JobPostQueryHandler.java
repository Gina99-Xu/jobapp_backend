package com.talentacquisition.career_portal_service.query.query;


import com.talentacquisition.career_portal_service.query.dto.JobPostQueryResponseDTO;
import com.talentacquisition.career_portal_service.query.repository.JobPost;
import com.talentacquisition.career_portal_service.query.repository.JobPostRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JobPostQueryHandler {

    @Autowired
    private JobPostRepository jobPostRepository;


    @QueryHandler
    public List<JobPostQueryResponseDTO> findAllJobPostQuery(FindAllJobPostsQuery findAllJobPostsQuery) {
        List<JobPostQueryResponseDTO> jobPostQueryResponseDTOList = new ArrayList<>();
        List<JobPost> jobPostList = jobPostRepository.findAll();
        for (JobPost jobPost : jobPostList) {
            JobPostQueryResponseDTO jobPostQueryResponseDTO = new JobPostQueryResponseDTO();
            BeanUtils.copyProperties(jobPost, jobPostQueryResponseDTO);
            jobPostQueryResponseDTOList.add(jobPostQueryResponseDTO);
        }
        return jobPostQueryResponseDTOList;
    }

    @QueryHandler
    public JobPostQueryResponseDTO findJobPostQuery(FindJobPostByJobPostIdQuery findJobPostByJobPostIdQuery) {
        JobPostQueryResponseDTO jobPostQueryResponseDTO = new JobPostQueryResponseDTO();
        JobPost jobPost = jobPostRepository.findById(findJobPostByJobPostIdQuery.getJobPostId()).get();
        BeanUtils.copyProperties(jobPost, jobPostQueryResponseDTO);
        return jobPostQueryResponseDTO;
    }

    @QueryHandler
    public List<JobPostQueryResponseDTO> findJobPostByEmploymentType(FindJobPostByEmploymentTypeQuery findJobPostByEmploymentTypeQuery) {
        List<JobPostQueryResponseDTO> jobPostQueryResponseDTOList = new ArrayList<>();
        List<JobPost> jobPostList = jobPostRepository.findByEmploymentType(findJobPostByEmploymentTypeQuery.getEmploymentType());
        for (JobPost jobPost : jobPostList) {
            JobPostQueryResponseDTO jobPostQueryResponseDTO = new JobPostQueryResponseDTO();
            BeanUtils.copyProperties(jobPost, jobPostQueryResponseDTO);
            jobPostQueryResponseDTOList.add(jobPostQueryResponseDTO);
        }
        return jobPostQueryResponseDTOList;
    }

    @QueryHandler
    public List<JobPostQueryResponseDTO> findJobPostByCoreSkillType(FindJobPostByCoreSkillTypeQuery findJobPostByCoreSkillTypeQuery) {
        List<JobPostQueryResponseDTO> jobPostQueryResponseDTOList = new ArrayList<>();
        List<JobPost> jobPostList = jobPostRepository.findByCandidateSkillsCoreSkill(findJobPostByCoreSkillTypeQuery.getCoreSkill());
        for (JobPost jobPost : jobPostList) {
            JobPostQueryResponseDTO jobPostQueryResponseDTO = new JobPostQueryResponseDTO();
            BeanUtils.copyProperties(jobPost, jobPostQueryResponseDTO);
            jobPostQueryResponseDTOList.add(jobPostQueryResponseDTO);
        }
        return jobPostQueryResponseDTOList;
    }

    @QueryHandler
    public List<JobPostQueryResponseDTO> findJobPostBySkillLevelType(FindJobPostBySkillLevelTypeQuery findJobPostBySkillLevelTypeQuery) {
        List<JobPostQueryResponseDTO> jobPostQueryResponseDTOList = new ArrayList<>();
        List<JobPost> jobPostList = jobPostRepository.findByCandidateSkillsSkillLevel(findJobPostBySkillLevelTypeQuery.getSkillLevel());
        for (JobPost jobPost : jobPostList) {
            JobPostQueryResponseDTO jobPostQueryResponseDTO = new JobPostQueryResponseDTO();
            BeanUtils.copyProperties(jobPost, jobPostQueryResponseDTO);
            jobPostQueryResponseDTOList.add(jobPostQueryResponseDTO);
        }
        return jobPostQueryResponseDTOList;
    }

    @QueryHandler
    public List<JobPostQueryResponseDTO> findJobPostByCoreSkillAndSkillLevelType(FindJobPostByCoreSkillAndSkillLevelTypeQuery findJobPostByCoreSkillAndSkillLevelTypeQuery) {
        List<JobPostQueryResponseDTO> jobPostQueryResponseDTOList = new ArrayList<>();
        List<JobPost> jobPostList = jobPostRepository.findByCandidateSkillsCoreSkillAndCandidateSkillsSkillLevel(findJobPostByCoreSkillAndSkillLevelTypeQuery.getCoreSkill(), findJobPostByCoreSkillAndSkillLevelTypeQuery.getSkillLevel());
        for (JobPost jobPost : jobPostList) {
            JobPostQueryResponseDTO jobPostQueryResponseDTO = new JobPostQueryResponseDTO();
            BeanUtils.copyProperties(jobPost, jobPostQueryResponseDTO);
            jobPostQueryResponseDTOList.add(jobPostQueryResponseDTO);
        }
        return jobPostQueryResponseDTOList;
    }

    @QueryHandler
    public List<JobPostQueryResponseDTO> findJobPostByRoleLevelType(FindJobPostByRoleLevelTypeQuery findJobPostByRoleLevelTypeQuery) {
        List<JobPostQueryResponseDTO> jobPostQueryResponseDTOList = new ArrayList<>();
        List<JobPost> jobPostList = jobPostRepository.findByRoleLevel(findJobPostByRoleLevelTypeQuery.getRoleLevel());
        for (JobPost jobPost : jobPostList) {
            JobPostQueryResponseDTO jobPostQueryResponseDTO = new JobPostQueryResponseDTO();
            BeanUtils.copyProperties(jobPost, jobPostQueryResponseDTO);
            jobPostQueryResponseDTOList.add(jobPostQueryResponseDTO);
        }
        return jobPostQueryResponseDTOList;
    }


}
