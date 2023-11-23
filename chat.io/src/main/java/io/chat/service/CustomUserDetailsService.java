//package io.chat.service;
//
//import io.chat.entity.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//
//    private final UserService userService;
//
//    @Autowired
//    public CustomUserDetailsService(UserService userService){
//        this.userService = userService;
//    }
//
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = userService.getUserByEmail(email).get();
//        List<String> roles = new ArrayList<>();
//        roles.add("USER");
//        UserDetails userDetails =
//                org.springframework.security.core.userdetails.User.builder()
//                        .username(user.getEmail())
//                        .password(user.getPassword())
//                        .roles(roles.toArray(new String[0]))
//                        .build();
//        return userDetails;
//    }
//}
