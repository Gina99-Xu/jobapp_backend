package com.talentacquisition.career_portal_service.query.query;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindJobPostByJobPostIdQuery {

    private String jobPostId;
}
