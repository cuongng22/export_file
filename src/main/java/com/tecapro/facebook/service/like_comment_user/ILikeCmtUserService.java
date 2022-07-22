package com.tecapro.facebook.service.like_comment_user;

import com.tecapro.facebook.model.entity.LikeComment;
import com.tecapro.facebook.service.IGeneralService;

import java.util.List;

public interface ILikeCmtUserService extends IGeneralService<LikeComment> {
    LikeComment findByCommentId(Long id);
    void deleteByPost(Long id);
}
