package com.tecapro.facebook.service.comment;

import com.tecapro.facebook.model.entity.CommentPostUser;
import com.tecapro.facebook.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService implements ICommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<CommentPostUser> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public CommentPostUser save(CommentPostUser commentPostUser) {
        return commentRepository.save(commentPostUser);
    }

    @Override
    public Optional<CommentPostUser> findById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<CommentPostUser> findAllByPostId(Long id) {
        return commentRepository.findAllByPostUser(id);
    }

    @Override
    public void deleteByPost(Long id) {
        commentRepository.deleteCommentByPostId(id);
    }
}
