package com.tecapro.facebook.service.post_user;

import com.tecapro.facebook.model.entity.PostUser;
import com.tecapro.facebook.service.IGeneralService;

import java.util.List;

public interface IPostUserService extends IGeneralService<PostUser> {
    List<PostUser> findByContent(String content);

    List<PostUser> findByUserId(Long id);
}
