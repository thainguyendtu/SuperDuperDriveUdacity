package com.udacity.jwdnd.course1.superduperdriver.controller;

import com.udacity.jwdnd.course1.superduperdriver.model.entities.FileEntity;
import com.udacity.jwdnd.course1.superduperdriver.service.FileService;
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

@Controller
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    public FileController(final FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile file, Model model) {
        if (fileService.addFile(file) > 0) {
            model.addAttribute("addFileSuccess", "Upload file success.");
        } else {
            model.addAttribute("addFileFail", "Upload file failure.");
        }
        return "redirect:/home";
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Integer id) throws Exception {
        try {
            FileEntity file = fileService.getFileDetail(id);
            return ResponseEntity.ok()
                                 .contentType(MediaType.parseMediaType(file.getContentType()))
                                 .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                                 .body(new ByteArrayResource(file.getData()));
        } catch (Exception e) {
            throw new Exception("Error downloading file");
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteFile(@PathVariable Integer id, Model model) {
        if (fileService.deleteFile(id) > 0) {
            model.addAttribute("updateNoteSuccess", "Insert note success.");
        } else {
            model.addAttribute("updateNoteFail", "Insert note failure.");
        }
        return "redirect:/home";
    }
}
