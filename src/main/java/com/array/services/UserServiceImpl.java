package com.array.services;

import com.array.entity.User;
import com.array.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author XIII
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

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
