package com.tecapro.facebook.service.like_post_user;

import com.tecapro.facebook.model.entity.LikePostUser;
import com.tecapro.facebook.repository.LikePostUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikePostUserService implements ILikePostUserService {
    @Autowired
    private LikePostUserRepository repository;
    @Override
    public List<LikePostUser> findAll() {
        return repository.findAll();
    }

    @Override
    public LikePostUser save(LikePostUser likePostUser) {
        return repository.save(likePostUser);
    }

    @Override
    public Optional<LikePostUser> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<LikePostUser> findAllByPostId(Long id) {
        return repository.findAllByPostUser(id);
    }

    @Override
    public void deleteByPost(Long id) {
        repository.deleteLikePostUser(id);
    }
}
