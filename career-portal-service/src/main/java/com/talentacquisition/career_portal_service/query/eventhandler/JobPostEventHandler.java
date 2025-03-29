package com.talentacquisition.career_portal_service.query.eventhandler;

import com.talentacquisition.career_portal_service.query.repository.JobPost;
import com.talentacquisition.career_portal_service.query.repository.JobPostRepository;
import com.talentacquisition.talent_core_api.event.JobPostCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobPostEventHandler {

    @Autowired
    private JobPostRepository jobPostRepository;


    @EventHandler
    public void on(JobPostCreatedEvent jobPostCreatedEvent) {
        JobPost jobPost = new JobPost();
        BeanUtils.copyProperties(jobPostCreatedEvent, jobPost);
        jobPostRepository.save(jobPost);
    }

}
