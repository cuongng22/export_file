package com.tecapro.facebook.repository;

import com.tecapro.facebook.model.entity.ReplyComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyCommentUserRepository extends JpaRepository<ReplyComment, Long> {
    @Query(value = "select * from reply_comment where post_user_id = ?1", nativeQuery = true)
    List<ReplyComment> findAllByCommentPostUser(Long id);

    @Query(value = "delete from reply_comment where post_user_id = ?1", nativeQuery = true)
    void deleteReplyComment(Long id);

}
