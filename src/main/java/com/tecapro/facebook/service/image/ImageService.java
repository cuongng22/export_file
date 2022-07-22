package com.tecapro.facebook.service.image;

import com.tecapro.facebook.model.entity.ImagePostUser;
import com.tecapro.facebook.repository.ImagePostUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService implements IImageService {
    @Autowired
    private ImagePostUserRepository repository;

    @Override
    public List<ImagePostUser> findAll() {
        return repository.findAll();
    }

    @Override
    public ImagePostUser save(ImagePostUser imagePostUser) {
        return repository.save(imagePostUser);
    }

    @Override
    public Optional<ImagePostUser> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<ImagePostUser> findAllByPostId(Long id) {
        return repository.findAllByPostUser(id);
    }

    @Override
    public void deleteByPost(Long id) {
        repository.deleteImage(id);
    }
}
