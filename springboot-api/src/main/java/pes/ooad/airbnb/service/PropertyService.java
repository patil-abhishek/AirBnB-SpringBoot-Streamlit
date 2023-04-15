package pes.ooad.airbnb.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pes.ooad.airbnb.converter.PropertyConverter;
import pes.ooad.airbnb.model.property.*;
import pes.ooad.airbnb.principal.CurrentUser;
import pes.ooad.airbnb.repository.PropertyRepository;
import pes.ooad.airbnb.repository.UserRepository;
import pes.ooad.airbnb.util.Helpers;

import java.util.List;

@Service
public class PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private UserRepository userRepository;

    public List<PropertyDisplay> getProperties() throws JsonProcessingException {
        return PropertyConverter.propertiesToPropertyDisplays((List<Property>) userRepository.findById(CurrentUser.user_id).get().getProperties());
    }

    public void addProperty(PropertyAdd propertyAdd) throws JsonProcessingException {
        Property property = PropertyConverter.propertyAddToProperty(propertyAdd);
        property.setHost(userRepository.findById(CurrentUser.user_id).get());
        propertyRepository.save(property);
    }

    public void deleteProperty(Integer propertyId) throws JsonProcessingException {
        propertyRepository.deleteById(propertyId);
    }

    public void updateProperty(Parameters parameters) {
        Property propertyToUpdate = propertyRepository.findById(parameters.getProperty_id()).get();
        propertyToUpdate.setPropertyName(parameters.getPropertyName());
        propertyToUpdate.setPrice(parameters.getPrice());
        propertyRepository.save(propertyToUpdate);
    }

    public ResponseEntity<String> searchProperties(SearchQuery searchQuery) throws JsonProcessingException {

//        String q = "Select * from property where property.city = ?1";
//        if (searchQuery.address != null) {
//            q += " and property.address = "+ searchQuery.address;
//        }
//        if(searchQuery.bedrooms != null) {
//            q += " and property.bedrooms = "+ searchQuery.bedrooms;
//        }
//        if(searchQuery.swimmingPool != null) {
//            q += " and property.swimmingPool = "+ searchQuery.swimmingPool;
//        }
//        if(searchQuery.parking != null) {
//            q += " and property.parking = "+ searchQuery.parking;
//        }
//        if(searchQuery.price != null) {
//            q += " and property.price <= "+ searchQuery.price;
//        }
//        if(searchQuery.averageRating != null) {
//            q += " and property.averageRating >= "+ searchQuery.averageRating;
//        }
//        @Query(q)
        List<Property> properties = (List<Property>) propertyRepository.findPropertyByCity(searchQuery.city);

        List<PropertyDisplay> propertyDisplays = PropertyConverter.propertiesToPropertyDisplays(properties);
        return ResponseEntity.ok().body(Helpers.convertToJson(propertyDisplays));

    }
}