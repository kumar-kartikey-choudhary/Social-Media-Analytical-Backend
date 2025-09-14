package com.socialanalyzer.user.repository;

import com.socialanalyzer.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
