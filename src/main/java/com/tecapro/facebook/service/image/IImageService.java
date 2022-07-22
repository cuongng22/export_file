package com.tecapro.facebook.service.image;

import com.tecapro.facebook.model.entity.ImagePostUser;
import com.tecapro.facebook.service.IGeneralService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IImageService extends IGeneralService<ImagePostUser> {
    List<ImagePostUser> findAllByPostId(Long id);
    void deleteByPost(Long id);
}
