package com.tecapro.facebook.service.like_comment_user;

import com.tecapro.facebook.model.entity.LikeComment;
import com.tecapro.facebook.repository.LikeCommentUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class LikeCmtUserService implements ILikeCmtUserService{
    @Autowired
    private LikeCommentUserRepository repository;

    @Override
    public List<LikeComment> findAll() {
        return repository.findAll();
    }

    @Override
    public LikeComment save(LikeComment likeComment) {
        return repository.save(likeComment);
    }

    @Override
    public Optional<LikeComment> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    public LikeComment findByCommentId(Long id) {
        return repository.findByCommentPostUser(id);
    }

    @Override
    public void deleteByPost(Long id) {
        repository.deleteComment(id);
    }
}
