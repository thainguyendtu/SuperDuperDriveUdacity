package com.udacity.jwdnd.course1.superduperdriver.model.entities;

public abstract class BaseEntity {

    private Boolean delFlag;
    private Integer userId;

    public Boolean isDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BaseEntity(Boolean delFlag, Integer userId) {
        this.delFlag = delFlag;
        this.userId = userId;
    }

    public BaseEntity() {
    }
}
