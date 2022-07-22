package com.tecapro.facebook.service.comment;

import com.tecapro.facebook.model.entity.CommentPostUser;
import com.tecapro.facebook.service.IGeneralService;

import java.util.List;

public interface ICommentService extends IGeneralService<CommentPostUser> {
    List<CommentPostUser> findAllByPostId(Long id);
    void deleteByPost(Long id);
}
