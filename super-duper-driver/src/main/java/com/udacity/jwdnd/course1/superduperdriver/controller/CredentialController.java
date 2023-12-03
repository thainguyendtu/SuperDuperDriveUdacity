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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/credential")
public class CredentialController {

    @Autowired
    CredentialService credentialService;

    @PostMapping("/add")
    public String addCredential(@ModelAttribute Credential credential,
                                RedirectAttributes redirectAttrs,
                                Principal principal) {
        if (principal == null) {
            return Constants.REDIRECT_LOGIN;
        }

        if (credentialService.addCredential(credential) > 0) {
            redirectAttrs.addFlashAttribute("message", "Insert credential success.");
        } else {
            redirectAttrs.addFlashAttribute("error", "Insert credential failed.");
        }

        return Constants.REDIRECT_HOME;
    }

    @PostMapping("/update")
    public String updateCredential(@ModelAttribute Credential credential,
                                   RedirectAttributes redirectAttrs,
                                   Principal principal) {
        if (principal == null) {
            return Constants.REDIRECT_LOGIN;
        }

        if (credentialService.updateCredential(credential) > 0) {
            redirectAttrs.addFlashAttribute("message", "Update credential success.");
        } else {
            redirectAttrs.addFlashAttribute("error", "Update credential failed.");
        }
        return Constants.REDIRECT_HOME;
    }

    @PostMapping("/delete/{id}")
    public String deleteCredential(@PathVariable Integer id,
                                   RedirectAttributes redirectAttrs,
                                   Principal principal) {
        if (principal == null) {
            return Constants.REDIRECT_LOGIN;
        }

        if (credentialService.deleteNote(id) > 0) {
            redirectAttrs.addFlashAttribute("message", "Delete credential success.");
        } else {
            redirectAttrs.addFlashAttribute("error", "Delete note failed.");
        }

        return Constants.REDIRECT_HOME;
    }
}
