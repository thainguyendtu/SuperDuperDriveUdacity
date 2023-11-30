package com.udacity.jwdnd.course1.superduperdriver.service;

import com.udacity.jwdnd.course1.superduperdriver.model.entities.FileEntity;
import com.udacity.jwdnd.course1.superduperdriver.model.mapper.FileMapper;
import com.udacity.jwdnd.course1.superduperdriver.util.ProjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class FileService {

    private final FileMapper fileMapper;
    private final ProjectUtils projectUtils;

    public FileService(FileMapper fileMapper, final ProjectUtils projectUtils) {
        this.fileMapper = fileMapper;
        this.projectUtils = projectUtils;
    }

    public List<FileEntity> getAllFiles() {
        return fileMapper.getAllFiles();
    }

    public FileEntity getFileDetail(int id) {
        return fileMapper.getFileDetail(id);
    }

    public int addFile(MultipartFile file) {
        try {
            return fileMapper.insert(
                    new FileEntity(file.getOriginalFilename(), file.getContentType(), getFileSizeBytes(file), file.getBytes(),
                                   false, projectUtils.getCurrentUserId()));

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int deleteFile(int id) {
        return fileMapper.delete(id);
    }

    private static String getFileSizeBytes(MultipartFile file) {
        return file.getSize() + " bytes";
    }
}
