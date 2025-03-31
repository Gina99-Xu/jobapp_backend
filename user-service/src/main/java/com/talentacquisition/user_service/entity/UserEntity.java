package com.talentacquisition.user_service.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	private String firstName;
	private String lastName;
	private int mobileNumber;

	@Column(unique = true)
	private String userEmail;

	@OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UserResumeAnalysisEntity> userJobAppliedEntityList;

}
