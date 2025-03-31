package com.talentacquisition.user_service.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class UserResumeAnalysisEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userResumeAnalysisId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity userEntity;

	private String jobPostId;

	@Lob
	private byte[] resumePdf;
	private String resumeFileName;

	private Double experienceScore;
	private Double skillsScore;
	private Double educationScore;
	private Double overallScore;

	@Column(columnDefinition = "TEXT")
	private String overallAnalysis;
}
