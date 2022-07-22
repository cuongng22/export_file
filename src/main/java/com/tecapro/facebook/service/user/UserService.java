package com.tecapro.facebook.service.user;

import com.tecapro.facebook.model.dto.UserPrincipal;
import com.tecapro.facebook.model.entity.Role;
import com.tecapro.facebook.model.entity.User;
import com.tecapro.facebook.model.entity.UserInfo;
import com.tecapro.facebook.repository.UserInfoRepository;
import com.tecapro.facebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository repository;
    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User save(User user) {
        String password = user.getPassword();
        String encodePassword = passwordEncoder.encode(password);//Mã hóa pass của người dùng
        user.setPassword(encodePassword);
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(2L, "ROLE_USER"));
        user.setRole("User");
        user.setRoles(roles);
        repository.save(user);
        UserInfo userInfo = new UserInfo(
                "user",
                "xx/yy/zzzz",
                "",
                "",
                "",
                "2f606ad14bf9171e5f41b42a01b4441f.jpg",
                "2f606ad14bf9171e5f41b42a01b4441f.jpg",
                user
        );
        userInfoRepository.save(userInfo);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        return UserPrincipal.build(user);
    }
}
