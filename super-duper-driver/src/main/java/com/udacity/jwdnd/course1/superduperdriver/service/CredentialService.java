package com.udacity.jwdnd.course1.superduperdriver.service;

import com.udacity.jwdnd.course1.superduperdriver.model.entities.Credential;
import com.udacity.jwdnd.course1.superduperdriver.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.superduperdriver.util.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Service
public class CredentialService {

    @Autowired
    CredentialMapper credentialMapper;

    @Autowired
    ProjectUtils projectUtils;

    @Autowired
    AuthenticationService authenticationService;

    public List<Credential> getAllCredential() {
        List<Credential> credentialsList = credentialMapper.getAllCredentials(authenticationService.getUserId());
        for(Credential credential : credentialsList) {
            credential.setDecodedPassword(
                    projectUtils.decodePassword(credential.getPassword(), credential.getKey()));
        }
        return credentialsList;
    }

    public int addCredential(Credential credential) {
        String encodedSalt = projectUtils.getEncodedSalt();
        String password = projectUtils.encodePassword(credential.getPassword(), encodedSalt);

        return credentialMapper.insert(
                new Credential(credential.getUrl(), credential.getUsername(), encodedSalt,
                               password, projectUtils.getCurrentUserId()));
    }

    public int updateCredential(Credential credential) {
        Credential currentData =
                credentialMapper.getCredentialDetail(credential.getCredentialId(), authenticationService.getUserId());

        if (Objects.isNull(currentData)) {
            return 0;
        }

        if (StringUtils.hasText(credential.getUrl()) && !credential.getUrl().equals(currentData.getUrl())) {
            currentData.setUrl(credential.getUrl());
        }

        if (StringUtils.hasText(credential.getUsername())
                && !credential.getUsername().equals(currentData.getUsername())) {
            currentData.setUsername(credential.getUsername());
        }

        if (StringUtils.hasText(credential.getPassword())) {
            String currentPassword = projectUtils.decodePassword(currentData.getPassword(), currentData.getKey());

            if (!currentPassword.equals(credential.getPassword())) {
                currentData.setPassword(
                        projectUtils.encodePassword(credential.getPassword(), currentData.getKey()));
            }
        }

        return credentialMapper.update(currentData);
    }

    public int deleteNote(Integer id) {
        return credentialMapper.delete(id);
    }
}
