//package io.chat.controller;
//
//import io.chat.auth.ErrorRes;
//import io.chat.auth.JwtUtil;
//import io.chat.auth.LoginReq;
//import io.chat.auth.LoginRes;
//import io.chat.dto.UserAuthDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@Controller
//@RequestMapping("/rest/auth")
//public class AuthController {
//    AuthenticationManager authenticationManager;
//    private JwtUtil jwtUtil;
//
//    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
//        this.authenticationManager = authenticationManager;
//        this.jwtUtil = jwtUtil;
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public ResponseEntity auth(@RequestBody LoginReq loginReq){
//        try {
//            // auth email with password
//            Authentication authentication =
//                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getEmail(), loginReq.getPassword()));
//            String email = authentication.getName();
//            System.out.println("Email: " + email);
//            // create object passed to create token
//            UserAuthDTO user = new UserAuthDTO(email,""); // I think no password need for JWT
//
//            // create token
//            String token = jwtUtil.createToken(user);
//
//            // return token in response
//            LoginRes loginRes = new LoginRes(email,token);
//
//            return ResponseEntity.ok(loginRes);
//
//        }catch (BadCredentialsException e){
//            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST,"Invalid username or password");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
//        }catch (Exception e){
//            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, e.getMessage());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
//        }
//    }
//
//}
