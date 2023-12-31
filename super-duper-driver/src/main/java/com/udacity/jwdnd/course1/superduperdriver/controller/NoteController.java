package com.udacity.jwdnd.course1.superduperdriver.controller;

import com.udacity.jwdnd.course1.superduperdriver.common.Constants;
import com.udacity.jwdnd.course1.superduperdriver.model.entities.Note;
import com.udacity.jwdnd.course1.superduperdriver.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/note")
public class NoteController {

    @Autowired
    NoteService noteService;

    @PostMapping("/add")
    public String addNote(@ModelAttribute Note note, RedirectAttributes redirectAttrs, Principal principal) {
        if (principal == null) {
            return Constants.REDIRECT_LOGIN;
        }

        if (noteService.addNote(note) > 0) {
            redirectAttrs.addFlashAttribute("message", "Insert note success.");
        } else {
            redirectAttrs.addFlashAttribute("error", "Insert note failed.");
        }

        return Constants.REDIRECT_HOME;
    }

    @PostMapping("/update")
    public String updateNote(@ModelAttribute Note note, RedirectAttributes redirectAttrs, Principal principal) {
        if (principal == null) {
            return Constants.REDIRECT_LOGIN;
        }

        if (noteService.updateNote(note) > 0) {
            redirectAttrs.addFlashAttribute("message", "Update note success.");
        } else {
            redirectAttrs.addFlashAttribute("error", "Update note failure.");
        }

        return Constants.REDIRECT_HOME;
    }

    @PostMapping("/delete/{id}")
    public String deleteNote(@PathVariable Integer id, RedirectAttributes redirectAttrs, Principal principal) {
        if (principal == null) {
            return Constants.REDIRECT_LOGIN;
        }

        if (noteService.deleteNote(id) > 0) {
            redirectAttrs.addFlashAttribute("message", "Delete note success.");
        } else {
            redirectAttrs.addFlashAttribute("error", "Delete note failure.");
        }

        return Constants.REDIRECT_HOME;
    }
}
