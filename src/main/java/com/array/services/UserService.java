package com.array.services;

import com.array.entity.User;

/**
 * @author XIII
 */
public interface UserService {

    boolean checkExists(String email);
    User createUser(User user);
    User updateUser(String id, User user);
}
