package com.tecapro.facebook.repository;

import com.tecapro.facebook.model.entity.PostUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostUserRepository extends JpaRepository<PostUser, Long> {
    List<PostUser> findByContentContaining(String content);
    @Query(value = "select * from post_user where user_id = ?1", nativeQuery = true)
    List<PostUser> findAllByUser(Long id);
}