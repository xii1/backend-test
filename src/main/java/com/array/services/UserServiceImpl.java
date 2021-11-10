package com.array.services;

import com.array.entity.User;
import com.array.entity.enums.Role;
import com.array.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author XIII
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserDetails() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserDetails) authentication.getPrincipal();
    }

    @Override
    public User loadUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public boolean checkExists(String email) {
        final User user = userRepository.findByEmail(email).orElse(null);
        if (user != null)
            return true;
        else
            return false;
    }

    @Override
    public User createUser(User user) {
        user.setId(null);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(String id, User user) {
        if (!Objects.isNull(id) && id.equals(user.getId())) {
            return userRepository.save(user);
        }
        return user;
    }
}
