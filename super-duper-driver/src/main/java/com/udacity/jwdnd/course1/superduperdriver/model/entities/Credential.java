package com.udacity.jwdnd.course1.superduperdriver.model.entities;

public class Credential extends BaseEntity {

    private Integer id;
    private String url;
    private String username;
    private String keyValue;
    private String password;
    private String decodedPassword;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDecodedPassword() {
        return decodedPassword;
    }

    public void setDecodedPassword(final String decodedPassword) {
        this.decodedPassword = decodedPassword;
    }

    public Credential(Integer id, String url, String username, String keyValue, String password) {
        this.id = id;
        this.url = url;
        this.username = username;
        this.keyValue = keyValue;
        this.password = password;
    }

    public Credential(String url, String username, String keyValue, String password, Boolean delFlag, Integer userId) {
        super(delFlag, userId);
        this.url = url;
        this.username = username;
        this.keyValue = keyValue;
        this.password = password;
    }

    public Credential() {
    }
}
