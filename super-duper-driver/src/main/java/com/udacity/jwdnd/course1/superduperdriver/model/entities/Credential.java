package com.udacity.jwdnd.course1.superduperdriver.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Credential extends BaseEntity {

    private Integer credentialId;
    private String url;
    private String username;
    private String key;
    private String password;
    private String decodedPassword;

    public Credential(String url, String username, String key, String password, Integer userId) {
        super(userId);
        this.url = url;
        this.username = username;
        this.key = key;
        this.password = password;
    }
}
