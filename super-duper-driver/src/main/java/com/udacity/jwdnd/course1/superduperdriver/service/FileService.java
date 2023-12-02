package com.udacity.jwdnd.course1.superduperdriver.service;

import com.udacity.jwdnd.course1.superduperdriver.model.entities.File;
import com.udacity.jwdnd.course1.superduperdriver.mapper.FileMapper;
import com.udacity.jwdnd.course1.superduperdriver.util.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class FileService {

    @Autowired
    FileMapper fileMapper;

    @Autowired
    ProjectUtils projectUtils;

    public List<File> getAllFiles() {
        return fileMapper.getAllFiles();
    }

    public File getFileDetail(int id) {
        return fileMapper.getFileDetail(id);
    }

    public boolean checkExistFileName(String fileName, int userId) {
        return fileMapper.checkExistFileName(fileName, userId) != null;
    }

    public int addFile(MultipartFile file) {
        try {
            return fileMapper.insert(
                    new File(file.getOriginalFilename(), file.getContentType(), getFileSizeBytes(file), file.getBytes(),
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
