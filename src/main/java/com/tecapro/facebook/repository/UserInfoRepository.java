package com.tecapro.facebook.repository;

import com.tecapro.facebook.model.entity.User;
import com.tecapro.facebook.model.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    @Query(value = "select * from user_info where user_id = ?1", nativeQuery = true)
    Optional<UserInfo> findByUserId(Long id);

    @Query(value = "select * from user_info where phone_number = ?1", nativeQuery = true)
    Optional<UserInfo> findByPhoneNumber(String phoneNumber);

    @Query(value = "select * from user_info where email = ?1", nativeQuery = true)
    List<UserInfo> findByEmail(String email);



}
