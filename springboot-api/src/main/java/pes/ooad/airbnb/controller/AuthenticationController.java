package pes.ooad.airbnb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pes.ooad.airbnb.converter.UserConverter;
import pes.ooad.airbnb.mailsender.VerifyMail;
import pes.ooad.airbnb.model.user.LoginCredentials;
import pes.ooad.airbnb.model.user.User;
import pes.ooad.airbnb.model.user.UserOTP;
import pes.ooad.airbnb.model.user.UserProfile;
import pes.ooad.airbnb.principal.CurrentUser;
import pes.ooad.airbnb.service.PropertyService;
import pes.ooad.airbnb.service.UserService;
import pes.ooad.airbnb.util.Helpers;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private VerifyMail verifyMail;

    @GetMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String email, @RequestParam String password) throws JsonProcessingException{
        if(userService.verifyLogin(email, password)){
            UserProfile userProfile = UserConverter.userToUserProfile(userService.findById(CurrentUser.user_id));
            return ResponseEntity.ok().body(Helpers.convertToJson(userProfile));
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("{Incorrect credentials}");
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logoutUser(){
        CurrentUser.user_id = -1;
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
//            UserProfile userProfile = UserConverter.userToUserProfile(userService.findById(CurrentUser.user_id));
            return ResponseEntity.ok().body("{Registered!}");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("{Incorrect OTP}");
        }
    }
}