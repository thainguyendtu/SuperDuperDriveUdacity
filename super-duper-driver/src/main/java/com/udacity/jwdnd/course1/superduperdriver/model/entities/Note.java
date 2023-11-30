package com.udacity.jwdnd.course1.superduperdriver.model.entities;

public class Note extends BaseEntity {

    private Integer id;
    private String title;
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Note(Integer id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Note(String title, String description, Boolean delFlag, Integer userId) {
        super(delFlag, userId);
        this.title = title;
        this.description = description;
    }

    public Note() {
    }
}
