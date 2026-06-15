package com.project.user.repository;

import com.project.user.model.UserInfo;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@NullMarked
public interface UserRepository extends JpaRepository<UserInfo, String> {
    boolean existsByEmail(String email);

    Boolean existsByKeycloakId(String userId);

    UserInfo findByEmail(String email);
}
