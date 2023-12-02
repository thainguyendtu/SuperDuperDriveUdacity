package com.udacity.jwdnd.course1.superduperdriver.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Note extends BaseEntity {

    private Integer id;
    private String title;
    private String description;

    public Note(String title, String description, Boolean delFlag, Integer userId) {
        super(delFlag, userId);
        this.title = title;
        this.description = description;
    }
}
