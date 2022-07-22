package com.tecapro.facebook.repository;

import com.tecapro.facebook.model.entity.CommentPostUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CommentRepository extends JpaRepository<CommentPostUser, Long> {
    @Query(value = "select * from comment_post_user where post_user_id = ?1", nativeQuery = true)
    List<CommentPostUser> findAllByPostUser(Long id);

    @Modifying
    @Query(value = "delete from comment_post_user where post_user_id = ?1", nativeQuery = true)
    void deleteCommentByPostId(Long id);
}
