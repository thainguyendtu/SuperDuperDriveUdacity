package com.udacity.jwdnd.course1.superduperdriver.util;

import com.udacity.jwdnd.course1.superduperdriver.model.entities.User;
import com.udacity.jwdnd.course1.superduperdriver.mapper.UserMapper;
import com.udacity.jwdnd.course1.superduperdriver.service.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;

@Component
public class ProjectUtils {

    @Autowired
    UserMapper userMapper;

    @Autowired
    EncryptionService encryptionService;

    public ProjectUtils(final UserMapper userMapper, final EncryptionService encryptionService) {
        this.userMapper = userMapper;
        this.encryptionService = encryptionService;
    }

    public Integer getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth instanceof AnonymousAuthenticationToken) {
            return null;
        }

        User currentUser = userMapper.getUser(auth.getName());
        return currentUser.getId();
    }

    public String getEncodedSalt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public String encodePassword(String password, String encodedSalt) {
        return encryptionService.encryptValue(password, encodedSalt);
    }

    public String decodePassword(String password, String encodedSalt) {
        return encryptionService.decryptValue(password, encodedSalt);
    }
}
