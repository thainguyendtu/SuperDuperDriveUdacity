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

    private Integer noteId;
    private String noteTitle;
    private String noteDescription;

    public Note(String noteTitle, String noteDescription, Integer userId) {
        super(userId);
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
    }
}
