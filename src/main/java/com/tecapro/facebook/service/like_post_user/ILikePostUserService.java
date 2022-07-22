package com.tecapro.facebook.service.like_post_user;

import com.tecapro.facebook.model.entity.LikePostUser;
import com.tecapro.facebook.service.IGeneralService;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ILikePostUserService extends IGeneralService<LikePostUser> {
    List<LikePostUser> findAllByPostId(Long id);
    void deleteByPost(Long id);
}
