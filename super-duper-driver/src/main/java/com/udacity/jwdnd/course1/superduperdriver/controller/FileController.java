package com.udacity.jwdnd.course1.superduperdriver.controller;

import com.udacity.jwdnd.course1.superduperdriver.common.Constants;
import com.udacity.jwdnd.course1.superduperdriver.config.FileUploadProperties;
import com.udacity.jwdnd.course1.superduperdriver.model.entities.File;
import com.udacity.jwdnd.course1.superduperdriver.service.AuthenticationService;
import com.udacity.jwdnd.course1.superduperdriver.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    FileService fileService;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    FileUploadProperties fileUploadProperties;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile file, Model model, Principal principal) {
        if (principal == null) {
            return Constants.REDIRECT_LOGIN;
        }

        int userId = authenticationService.getUserId();
        long maxFileSize = convertFileSize(fileUploadProperties.getMaxFileSize());
        if (file.isEmpty()) {
            model.addAttribute("error", "Please select a file to upload.");
        } else if (file.getSize() > maxFileSize) {
            model.addAttribute("error", "Please upload file under 10MB in size.");
        } else if (fileService.checkExistFileName(file.getOriginalFilename(), userId)) {
            model.addAttribute("error", "File name is already exists.");
        } else if (fileService.addFile(file) < 1) {
            model.addAttribute("error", "Upload file failed.");
        } else {
            model.addAttribute("message", "Upload file success.");
        }

        return Constants.REDIRECT_HOME;
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Integer id) throws Exception {
        try {
            File file = fileService.getFileDetail(id);
            return ResponseEntity.ok()
                                 .contentType(MediaType.parseMediaType(file.getContentType()))
                                 .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                                 .body(new ByteArrayResource(file.getData()));
        } catch (Exception e) {
            throw new Exception("Error downloading file");
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteFile(@PathVariable Integer id, Model model, Principal principal) {
        if (principal == null) {
            return Constants.REDIRECT_LOGIN;
        }

        if (fileService.deleteFile(id) > 0) {
            model.addAttribute("updateNoteSuccess", "Insert note success.");
        } else {
            model.addAttribute("updateNoteFail", "Insert note failure.");
        }
        return Constants.REDIRECT_HOME;
    }

    private long convertFileSize(String fileSize) {
        String sizeValue = fileSize.replaceAll("[^\\d.]", "");
        if (fileSize.endsWith("MB")) {
            return (long) (Double.parseDouble(sizeValue) * 1024 * 1024);
        } else if (fileSize.endsWith("KB")) {
            return (long) (Double.parseDouble(sizeValue) * 1024);
        }
        return Long.parseLong(sizeValue);
    }
}
