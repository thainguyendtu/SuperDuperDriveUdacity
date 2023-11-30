package com.udacity.jwdnd.course1.superduperdriver.controller;

import com.udacity.jwdnd.course1.superduperdriver.model.entities.Credential;
import com.udacity.jwdnd.course1.superduperdriver.service.CredentialService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/credential")
public class CredentialController {

    private final CredentialService credentialService;

    public CredentialController(final CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @PostMapping()
    public String addCredential(@ModelAttribute Credential credential, Model model) {
        if (credentialService.addCredential(credential) > 0) {
            model.addAttribute("addCredentialSuccess", "Insert credential success.");
        } else {
            model.addAttribute("addCredentialFail", "Insert credential failure.");
        }
        return "redirect:/home";
    }

    @PostMapping("/update")
    public String updateCredential(@ModelAttribute Credential credential, Model model) {
        if (credentialService.updateCredential(credential) > 0) {
            model.addAttribute("updateNoteSuccess", "Insert note success.");
        } else {
            model.addAttribute("updateNoteFail", "Insert note failure.");
        }
        return "redirect:/home";
    }

    @PostMapping("/delete/{id}")
    public String deleteCredential(@PathVariable Integer id, Model model) {
        if (credentialService.deleteNote(id) > 0) {
            model.addAttribute("updateNoteSuccess", "Insert note success.");
        } else {
            model.addAttribute("updateNoteFail", "Insert note failure.");
        }
        return "redirect:/home";
    }
}
