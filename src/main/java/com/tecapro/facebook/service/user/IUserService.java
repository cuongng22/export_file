package com.tecapro.facebook.service.user;

import com.tecapro.facebook.model.entity.User;
import com.tecapro.facebook.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    User findByUsername(String username);
}
