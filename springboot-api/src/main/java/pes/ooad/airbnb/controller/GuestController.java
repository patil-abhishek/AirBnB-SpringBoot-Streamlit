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
@RequestMapping("/api/guest")
@CrossOrigin(origins = "*", maxAge = 3600)
public class GuestController {

    @Autowired
    private UserService userService;

    @Autowired
    private PropertyService propertyService;

    @GetMapping(path="/searchProperty")
    public ResponseEntity<String> searchProperty(
            @RequestParam String city, @RequestParam(required = false) String address,
            @RequestParam(required = false) Integer bedrooms, @RequestParam(required = false) Integer price,
            @RequestParam(required = false) Boolean swimmingPool, @RequestParam(required = false) Boolean parking,
            @RequestParam(required = false) Double averageRating ) throws JsonProcessingException
    {
        return ResponseEntity.ok().body(Helpers.convertToJson(propertyService.searchProperties(
                city, address, bedrooms, price, swimmingPool, parking, averageRating )));
    }

    @PostMapping(path="/favProperty")
    public ResponseEntity<String> favProperty(@RequestBody Integer property_id) {
        userService.addPropertyToFav(property_id);
        return ResponseEntity.ok().body("Fav'ed!");
    }

    @PostMapping(path="/unFavProperty")
    public ResponseEntity<String> unFavProperty(@RequestBody Integer property_id){
        userService.removePropertyFav(property_id);
        return ResponseEntity.ok().body("Unfav'ed");
    }

}