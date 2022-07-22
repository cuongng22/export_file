package com.tecapro.facebook.repository;

import com.tecapro.facebook.model.entity.CommentPostUser;
import com.tecapro.facebook.model.entity.LikeComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeCommentUserRepository extends JpaRepository<LikeComment, Long> {
    @Query(value = "select * from like_comment where comment_post_user_id = ?1", nativeQuery = true)
    LikeComment findByCommentPostUser(Long id);

    @Query(value = "delete from like_comment where comment_post_user_id = ?1", nativeQuery = true)
    void deleteComment(Long id);
}
