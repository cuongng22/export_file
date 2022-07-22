package com.tecapro.facebook.service.user_info;

import com.tecapro.facebook.model.entity.UserInfo;
import com.tecapro.facebook.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserInfoService implements IUserInfoService {
    @Autowired
    private UserInfoRepository repository;
    @Override
    public List<UserInfo> findAll() {
        return repository.findAll();
    }

    @Override
    public UserInfo save(UserInfo userInfo) {
        return repository.save(userInfo);
    }

    @Override
    public Optional<UserInfo> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<UserInfo> findByUserId(Long id) {
        return repository.findByUserId(id);
    }

    @Override
    public Optional<UserInfo> findByPhoneNumber(String phoneNumber) {
        return repository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public List<UserInfo> findByEmail(String email) {
        return repository.findByEmail(email);
    }
}
