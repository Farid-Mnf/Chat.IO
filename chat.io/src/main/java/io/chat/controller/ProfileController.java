package io.chat.controller;

import io.chat.entity.User;
import io.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProfileController {
    UserService userService;

    @Autowired
    public ProfileController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/profile/{userId}")
    public String profilePage(@PathVariable("userId") long userId, Model model){
        User user = userService.getUser(userId).get();
        model.addAttribute("user", user);
        return "profile";
    }

}
