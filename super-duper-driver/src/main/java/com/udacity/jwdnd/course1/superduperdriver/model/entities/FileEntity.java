package com.udacity.jwdnd.course1.superduperdriver.model.entities;

public class FileEntity extends BaseEntity {

    private Integer id;
    private String fileName;
    private String contentType;
    private String size;
    private byte[] data;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(final byte[] data) {
        this.data = data;
    }

    public FileEntity(Integer id, String fileName, String contentType, String size, byte[] data) {
        this.id = id;
        this.fileName = fileName;
        this.contentType = contentType;
        this.size = size;
        this.data = data;
    }

    public FileEntity(String fileName, String contentType, String size, byte[] data, Boolean delFlag, Integer userId) {
        super(delFlag, userId);
        this.fileName = fileName;
        this.contentType = contentType;
        this.size = size;
        this.data = data;
    }

    public FileEntity() {
    }
}
