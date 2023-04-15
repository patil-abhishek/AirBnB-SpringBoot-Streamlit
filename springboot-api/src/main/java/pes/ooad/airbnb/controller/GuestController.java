package pes.ooad.airbnb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pes.ooad.airbnb.model.property.SearchQuery;
import pes.ooad.airbnb.service.PropertyService;

@RestController
@RequestMapping("/api/guest")
@CrossOrigin(origins = "*", maxAge = 3600)
public class GuestController {
    @Autowired
    private PropertyService propertyService;

//    @GetMapping("/searchProperties")
//    public ResponseEntity<String> searchProperties(@RequestParam String city) {
//        return propertyService.searchProperties();
//    }



}
