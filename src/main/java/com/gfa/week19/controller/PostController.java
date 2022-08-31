package com.gfa.week19.controller;

import com.gfa.week19.model.Post;
import com.gfa.week19.service.PostService;
import com.gfa.week19.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class PostController {

    private final UserService userService;
    private final PostService postService;
    private final int PAGE_SIZE = 10;
    private boolean myPosts;

    @GetMapping("/")
    public String listPosts(Model model, @RequestParam(required = false, defaultValue = "0") Integer page) {
        model.addAttribute("user", userService.getLoggedInUser());
        model.addAttribute("page", page);
        if (myPosts) {
            model.addAttribute("posts", postService.getLoggedInUserPosts(page, PAGE_SIZE));
        } else {
            model.addAttribute("posts", postService.findAll(page, PAGE_SIZE));
        }
        return "index";
    }

    @GetMapping("/my")
    public String getPostsByUser() {
        if (userService.isLoggedIn()) {
            myPosts = true;
            return "redirect:/";
        }
        return "redirect:/login";
    }

    @GetMapping("/all")
    public String getAllPosts() {
        if (userService.isLoggedIn()) {
            myPosts = false;
            return "redirect:/";
        }
        return "redirect:/login";
    }

    @GetMapping("/vote/{id}/{up}")
    public String votePost(@PathVariable Long id, @PathVariable Boolean up) {
        if (userService.isLoggedIn()) {
            postService.votePost(id, up);
            return "redirect:/";
        }
        return "redirect:/login";
    }

    @GetMapping("/submit")
    public String submitPost(@ModelAttribute Post post) {
        return (userService.isLoggedIn()) ? "submit" : "redirect:/login";
    }

    @PostMapping("/submit")
    public String getSubmitForm(@ModelAttribute Post post) {
        if (userService.isLoggedIn()) {
            postService.addPost(post);
            return "redirect:/";
        }
        return "redirect:/login";
    }
}