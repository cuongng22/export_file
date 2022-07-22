package com.tecapro.facebook.repository;

import com.tecapro.facebook.model.entity.ImagePostUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ImagePostUserRepository extends JpaRepository<ImagePostUser, Long> {
    @Query(value = "select * from image_post_user where post_user_id = ?1", nativeQuery = true)
    List<ImagePostUser> findAllByPostUser(Long id);

    @Modifying
    @Query(value = "delete from image_post_user where post_user_id = ?1", nativeQuery = true)
    void deleteImage(Long id);
}
