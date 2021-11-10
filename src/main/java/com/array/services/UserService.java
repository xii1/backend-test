package com.array.services;

import com.array.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author XIII
 */
public interface UserService {
    UserDetails loadUserDetails();

    User loadUserByEmail(String email);

    boolean checkExists(String email);

    User createUser(User user);

    User updateUser(String id, User user);
}
