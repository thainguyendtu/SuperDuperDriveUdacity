package com.udacity.jwdnd.course1.superduperdriver.service;

import com.udacity.jwdnd.course1.superduperdriver.model.entities.User;
import com.udacity.jwdnd.course1.superduperdriver.model.mapper.UserMapper;
import com.udacity.jwdnd.course1.superduperdriver.util.ProjectUtils;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final ProjectUtils projectUtils;

    public UserService(UserMapper userMapper, ProjectUtils projectUtils) {
        this.userMapper = userMapper;
        this.projectUtils = projectUtils;
    }

    public boolean isUsernameAvailable(String username) {
        return userMapper.getUser(username) == null;
    }

    public int createUser(User user) {
        String encodedSalt = projectUtils.getEncodedSalt();
        String hashedPassword = projectUtils.encodePassword(user.getPassword(), encodedSalt);
        return userMapper.insert(new User(user.getUsername(), encodedSalt, hashedPassword, user.getFirstName(), user.getLastName()));
    }

    public User getUser(String username) {
        return userMapper.getUser(username);
    }
}
