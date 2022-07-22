package com.tecapro.facebook.service.reply_comment_user;

import com.tecapro.facebook.model.entity.ReplyComment;
import com.tecapro.facebook.service.IGeneralService;

import java.util.List;

public interface IReplyCommentService extends IGeneralService<ReplyComment> {
    List<ReplyComment> findAllByCommentId(Long id);
    void deleteByPost(Long id);
}
