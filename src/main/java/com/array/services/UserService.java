package com.array.services;

import com.array.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * @author XIII
 */
public interface UserService {
    UserDetails loadUserDetails();

    User loadUserByEmail(String email);

    User getCurrentUser();

    boolean checkExists(String email);

    User createUser(User user);

    User getUserById(Long userId);

    User updateRole(User user, List<String> roles);
}
