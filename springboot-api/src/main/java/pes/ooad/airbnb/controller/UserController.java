package pes.ooad.airbnb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pes.ooad.airbnb.converter.UserConverter;
import pes.ooad.airbnb.model.property.Parameters;
import pes.ooad.airbnb.model.property.Property;
import pes.ooad.airbnb.model.property.PropertyAdd;
import pes.ooad.airbnb.model.property.PropertyDisplay;
import pes.ooad.airbnb.model.user.User;
import pes.ooad.airbnb.model.user.UserProfile;
import pes.ooad.airbnb.principal.CurrentUser;
import pes.ooad.airbnb.service.PropertyService;
import pes.ooad.airbnb.service.UserService;
import pes.ooad.airbnb.util.Helpers;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PropertyService propertyService;

    @GetMapping("/profile")
    public ResponseEntity<String> getUserDetails() throws JsonProcessingException {
        if (CurrentUser.user_id == -1)
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("{No user Logged in}");
        UserProfile userProfile = UserConverter.userToUserProfile(userService.findById(CurrentUser.user_id));
        return ResponseEntity.ok().body(Helpers.convertToJson(userProfile));
    }

    @PostMapping("/updateProfile")
    public ResponseEntity<String> updateProfile(@RequestBody User user) throws JsonProcessingException {
        userService.updateUser(user);
        return getUserDetails();
    }

    @GetMapping("/myProperties")
    public ResponseEntity<String> getProperties() throws JsonProcessingException {
        return ResponseEntity.ok().body(Helpers.convertToJson(propertyService.getProperties()));
    }

    @PostMapping("/addProperty")
    public ResponseEntity<String> addProperty(@RequestBody PropertyAdd propertyAdd) throws JsonProcessingException {
        propertyService.addProperty(propertyAdd);
        return getProperties();
    }


    @PostMapping("/deleteProperty")
    public ResponseEntity<String> deleteProperty(@RequestBody Integer property_id) throws JsonProcessingException {
        propertyService.deleteProperty(property_id);
        return getProperties();
    }

    @PostMapping("/updateProperty")
    public ResponseEntity<String> updateProperty(@RequestBody Parameters parameters) throws JsonProcessingException {
        propertyService.updateProperty(parameters);
        return getProperties();
    }
}