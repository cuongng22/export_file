package com.tecapro.facebook.service.post_user;

import com.tecapro.facebook.model.entity.PostUser;
import com.tecapro.facebook.repository.PostUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostUserService implements IPostUserService {
    @Autowired
    private PostUserRepository postUserRepository;

    @Override
    public List<PostUser> findAll() {
        return postUserRepository.findAll();
    }

    @Override
    public PostUser save(PostUser postUser) {
        return postUserRepository.save(postUser);
    }

    @Override
    public Optional<PostUser> findById(Long id) {
        return postUserRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        postUserRepository.deleteById(id);
    }

    @Override
    public List<PostUser> findByContent(String content) {
        return postUserRepository.findByContentContaining(content);
    }

    @Override
    public List<PostUser> findByUserId(Long id) {
        return postUserRepository.findAllByUser(id);
    }
}
