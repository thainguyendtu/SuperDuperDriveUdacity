package com.udacity.jwdnd.course1.superduperdriver.service;

import com.udacity.jwdnd.course1.superduperdriver.model.entities.User;
import com.udacity.jwdnd.course1.superduperdriver.mapper.UserMapper;
import com.udacity.jwdnd.course1.superduperdriver.util.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    ProjectUtils projectUtils;

    @Autowired
    HashService hashService;

    public boolean isUsernameAvailable(String username) {
        return Objects.isNull(userMapper.getUser(username));
    }

    public int createUser(User user) {
        String encodedSalt = projectUtils.getEncodedSalt();
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        return userMapper.insert(
                new User(user.getUsername(), encodedSalt, hashedPassword, user.getFirstName(), user.getLastName()));
    }
}
