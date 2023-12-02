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

    private Integer id;
    private String url;
    private String username;
    private String keyValue;
    private String password;
    private String decodedPassword;

    public Credential(String url, String username, String keyValue, String password, Boolean delFlag, Integer userId) {
        super(delFlag, userId);
        this.url = url;
        this.username = username;
        this.keyValue = keyValue;
        this.password = password;
    }
}
