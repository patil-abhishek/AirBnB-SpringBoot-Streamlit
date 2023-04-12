package pes.ooad.airbnb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pes.ooad.airbnb.converter.UserConverter;
import pes.ooad.airbnb.mailsender.VerifyMail;
import pes.ooad.airbnb.model.user.LoginCredentials;
import pes.ooad.airbnb.model.user.User;
import pes.ooad.airbnb.model.user.UserOTP;
import pes.ooad.airbnb.model.user.UserProfile;
import pes.ooad.airbnb.principal.CurrentUser;
import pes.ooad.airbnb.service.UserService;
import pes.ooad.airbnb.util.Helpers;

@Controller
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private VerifyMail verifyMail;

    @GetMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginCredentials loginCredentials) throws JsonProcessingException{
        if(userService.verifyLogin(loginCredentials)){
            UserProfile userProfile = new UserProfile(CurrentUser.user.getFirstname(),
                    CurrentUser.user.getLastname(), CurrentUser.user.getEmail(), CurrentUser.user.getPhone());
            return ResponseEntity.ok().body(Helpers.convertToJson(userProfile));
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("{Incorrect credentials}");
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logoutUser(){
        CurrentUser.user = null;
        return ResponseEntity.ok().body("Logged out successfully");
    }

    @PostMapping("/verify")
    public ResponseEntity<String> addNewUser(@RequestBody User user){
        if(!userService.checkUser(user.getEmail(), user.getPhone()))
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("{User already exists}");
        verifyMail.sendEmail(user.getEmail(), user.getFirstname(), user.getLastname());
        return ResponseEntity.ok().body("{Enter otp}");
    }

    @PostMapping("/register")
    public ResponseEntity<String> verifyNewUser(@RequestBody UserOTP userOTP) throws JsonProcessingException{
        User user = UserConverter.userOTPtoUser(userOTP);
        System.out.println(userOTP.getInputOTP());
        System.out.println(CurrentUser.otp);
        if(CurrentUser.otp.equals(userOTP.getInputOTP())){
            userService.addUserToDB(user);
            return ResponseEntity.ok().body(Helpers.convertToJson(CurrentUser.user));
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("{Incorrect OTP}");
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<String> getUserDetails() throws JsonProcessingException {
        if (CurrentUser.user == null)
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("{No user Logged in}");
        UserProfile userProfile = new UserProfile(CurrentUser.user.getFirstname(),
                CurrentUser.user.getLastname(), CurrentUser.user.getEmail(), CurrentUser.user.getPhone());
        return ResponseEntity.ok().body(Helpers.convertToJson(userProfile));
    }


}