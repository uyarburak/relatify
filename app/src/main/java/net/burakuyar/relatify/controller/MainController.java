package net.burakuyar.relatify.controller;

import net.burakuyar.relatify.model.User;
import net.burakuyar.relatify.service.UserService;
import net.burakuyar.relatify.util.JSONExport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Created by burak on 2/25/2018.
 */
@Controller
public class MainController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    String index() {
        return "index";
    }

    @PostMapping("/upload")
    String upload(@RequestParam("email") String email, @RequestParam("document") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()){
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/";
        }
        try {
            InputStream pdfStream = new ByteArrayInputStream(file.getBytes());
            User user = userService.getUser(email, pdfStream);
            redirectAttributes.addFlashAttribute("message", userService.save(user));
            return "redirect:/";
        }catch (Exception exception){
            exception.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "Broken file!");
            return "redirect:/";
        }
    }
}
