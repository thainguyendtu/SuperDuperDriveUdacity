package com.udacity.jwdnd.course1.superduperdriver.controller;

import com.udacity.jwdnd.course1.superduperdriver.common.Constants;
import com.udacity.jwdnd.course1.superduperdriver.model.entities.Credential;
import com.udacity.jwdnd.course1.superduperdriver.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/credential")
public class CredentialController {

    @Autowired
    CredentialService credentialService;

    @PostMapping("/add")
    public String addCredential(@ModelAttribute Credential credential, Model model, Principal principal) {
        if (principal == null) {
            return Constants.REDIRECT_LOGIN;
        }

        if (credentialService.addCredential(credential) > 0) {
            model.addAttribute("message", "Insert credential success.");
        } else {
            model.addAttribute("error", "Insert credential failed.");
        }

        return Constants.REDIRECT_HOME;
    }

    @PostMapping("/update")
    public String updateCredential(@ModelAttribute Credential credential, Model model, Principal principal) {
        if (principal == null) {
            return Constants.REDIRECT_LOGIN;
        }

        if (credentialService.updateCredential(credential) > 0) {
            model.addAttribute("message", "Update credential success.");
        } else {
            model.addAttribute("error", "Update credential failed.");
        }
        return Constants.REDIRECT_HOME;
    }

    @PostMapping("/delete/{id}")
    public String deleteCredential(@PathVariable Integer id, Model model, Principal principal) {
        if (principal == null) {
            return Constants.REDIRECT_LOGIN;
        }

        if (credentialService.deleteNote(id) > 0) {
            model.addAttribute("message", "Delete credential success.");
        } else {
            model.addAttribute("error", "Delete note failed.");
        }

        return Constants.REDIRECT_HOME;
    }
}
