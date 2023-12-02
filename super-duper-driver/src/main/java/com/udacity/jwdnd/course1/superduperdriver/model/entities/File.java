package com.udacity.jwdnd.course1.superduperdriver.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class File extends BaseEntity {

    private Integer id;
    private String fileName;
    private String contentType;
    private String size;
    private byte[] data;

    public File(String fileName, String contentType, String size, byte[] data, Boolean delFlag, Integer userId) {
        super(delFlag, userId);
        this.fileName = fileName;
        this.contentType = contentType;
        this.size = size;
        this.data = data;
    }
}
