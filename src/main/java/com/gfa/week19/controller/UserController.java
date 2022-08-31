package com.gfa.week19.controller;

import com.gfa.week19.model.User;
import com.gfa.week19.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping({"/register", "login"})
    public String registerForm(@ModelAttribute User user) {
        return "login";
    }

    @PostMapping({"/register", "login"})
    public String getRegisterForm(@ModelAttribute User user) {
        if (userService.loginUser(user)) {
            return "redirect:/";
        }
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logoutUser() {
        userService.logoutUser();
        return "redirect:/";
    }
}