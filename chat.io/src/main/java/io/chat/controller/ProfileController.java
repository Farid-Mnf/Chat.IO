package io.chat.controller;

import com.fasterxml.jackson.databind.ser.std.DateTimeSerializerBase;
import io.chat.dto.ImageFile;
import io.chat.dto.UserDTO;
import io.chat.entity.User;
import io.chat.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.Loader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

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

    @PostMapping("/profileImage")
    public ResponseEntity<String> profileImage(@ModelAttribute ImageFile imageFile, @ModelAttribute UserDTO userDTO, HttpServletRequest request) throws IOException {


        String photoName = UUID.randomUUID().toString();
        String fileType = imageFile.getFile().getContentType();
        String fullPhotoName = photoName + "." + fileType.split("/")[1];



        File file = new File(ClassLoader.getSystemResource(".").getFile() + "/static/images/profile-images/" + fullPhotoName);
        if (file.createNewFile()) {
            imageFile.getFile().transferTo(file);
            if(userService.getUser(userDTO.getId()).isPresent()){
                User user = userService.getUser(userDTO.getId()).get();
                user.setImage(fullPhotoName);
                userService.saveOrUpdate(user);
            }
        }

        return ResponseEntity.ok(fullPhotoName);

    }

}
