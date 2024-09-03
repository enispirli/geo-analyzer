package com.interview.geoanalyzer.repository;

import com.interview.geoanalyzer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
