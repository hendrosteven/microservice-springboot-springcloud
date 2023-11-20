package com.kelaskoding.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kelaskoding.entity.UserInfo;

public interface UserInfoRepo extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByName(String name);
}
