package com.tecapro.facebook.service.reply_comment_user;

import com.tecapro.facebook.model.entity.ReplyComment;
import com.tecapro.facebook.repository.ReplyCommentUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReplyCommentService implements IReplyCommentService {
    @Autowired
    private ReplyCommentUserRepository repository;
    @Override
    public List<ReplyComment> findAll() {
        return repository.findAll();
    }

    @Override
    public ReplyComment save(ReplyComment replyComment) {
        return repository.save(replyComment);
    }

    @Override
    public Optional<ReplyComment> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<ReplyComment> findAllByCommentId(Long id) {
        return repository.findAllByCommentPostUser(id);
    }

    @Override
    public void deleteByPost(Long id) {
        repository.deleteReplyComment(id);
    }
}
