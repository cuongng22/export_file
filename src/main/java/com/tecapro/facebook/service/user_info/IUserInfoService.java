package com.tecapro.facebook.service.user_info;

import com.tecapro.facebook.model.entity.UserInfo;
import com.tecapro.facebook.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface IUserInfoService extends IGeneralService<UserInfo> {
    Optional<UserInfo> findByUserId(Long id);
    Optional<UserInfo> findByPhoneNumber(String phoneNumber);
    List<UserInfo> findByEmail(String email);
}
