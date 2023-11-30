package com.udacity.jwdnd.course1.superduperdriver.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final FileService fileService;
    private final NoteService noteService;
    private final CredentialService credentialService;

    public HomeController(final FileService fileService, final NoteService noteService, final CredentialService credentialService) {
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
    }

    @GetMapping
    public String home(Model model) {
        model.addAttribute("filesList", fileService.getAllFiles());
        model.addAttribute("notesList", noteService.getAllNotes());
        model.addAttribute("credentialsList", credentialService.getAllCredential());

        return "home";
    }
}
