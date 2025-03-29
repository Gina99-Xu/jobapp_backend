package com.talentacquisition.user_service.repository;

import com.talentacquisition.user_service.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findUserByUserEmail(String userEmail);
}
