package com.tecapro.facebook.repository;

import com.tecapro.facebook.model.entity.LikePostUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface LikePostUserRepository extends JpaRepository<LikePostUser, Long> {
    @Query(value = "select * from like_post_user where post_user_id = ?1", nativeQuery = true)
    List<LikePostUser> findAllByPostUser(Long id);


    @Modifying
    @Query(value = "delete from like_post_user where post_user_id = ?1", nativeQuery = true)
    void deleteLikePostUser(Long id);
}
