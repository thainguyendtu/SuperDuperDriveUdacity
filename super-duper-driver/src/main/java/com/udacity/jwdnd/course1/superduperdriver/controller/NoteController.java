package com.udacity.jwdnd.course1.superduperdriver.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.entities.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/note")
public class NoteController {

    private final NoteService noteService;

    public NoteController(final NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping()
    public String addNote(@ModelAttribute Note note, Model model) {
        if (noteService.addNote(note) > 0) {
            model.addAttribute("addNoteSuccess", "Insert note success.");
        } else {
            model.addAttribute("addNoteFail", "Insert note failure.");
        }
        return "redirect:/home";
    }

    @PostMapping("/update")
    public String updateNote(@ModelAttribute Note note, Model model) {
        if (noteService.updateNote(note) > 0) {
            model.addAttribute("updateNoteSuccess", "Insert note success.");
        } else {
            model.addAttribute("updateNoteFail", "Insert note failure.");
        }
        return "redirect:/home";
    }

    @PostMapping("/delete/{id}")
    public String deleteNote(@PathVariable Integer id, Model model) {
        if (noteService.deleteNote(id) > 0) {
            model.addAttribute("updateNoteSuccess", "Insert note success.");
        } else {
            model.addAttribute("updateNoteFail", "Insert note failure.");
        }
        return "redirect:/home";
    }
}
