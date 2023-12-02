package com.udacity.jwdnd.course1.superduperdriver.controller;

import com.udacity.jwdnd.course1.superduperdriver.common.Constants;
import com.udacity.jwdnd.course1.superduperdriver.service.CredentialService;
import com.udacity.jwdnd.course1.superduperdriver.service.FileService;
import com.udacity.jwdnd.course1.superduperdriver.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Objects;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    FileService fileService;

    @Autowired
    NoteService noteService;

    @Autowired
    CredentialService credentialService;

    @GetMapping
    public String home(Model model, Principal principal) {
        if (Objects.isNull(principal)) {
            return Constants.REDIRECT_LOGIN;
        }

        model.addAttribute("filesList", fileService.getAllFiles());
        model.addAttribute("notesList", noteService.getAllNotes());
        model.addAttribute("credentialsList", credentialService.getAllCredential());

        return "home";
    }
}
