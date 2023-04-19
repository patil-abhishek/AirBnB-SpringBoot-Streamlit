package pes.ooad.airbnb.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pes.ooad.airbnb.converter.PropertyConverter;
import pes.ooad.airbnb.model.property.*;
import pes.ooad.airbnb.principal.CurrentUser;
import pes.ooad.airbnb.repository.PropertyRepository;
import pes.ooad.airbnb.repository.UserRepository;
import pes.ooad.airbnb.util.Helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyService {

    @PersistenceContext
    private EntityManager entityManager;

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

    public List<PropertyDisplay> searchProperties(String city, String address, Integer bedrooms, Integer price,
                                                  Boolean swimmingPool, Boolean parking, Double averageRating )
    {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Property> query = builder.createQuery(Property.class);
        Root<Property> root = query.from(Property.class);
        root.join("host", JoinType.LEFT);

        List<Predicate> predicates = new ArrayList<>();
        if (city != null && city != "") {
            predicates.add(builder.equal(root.get("city"), city));
        }
        if (address != null && address != "") {
            predicates.add(builder.equal(root.get("address"), address));
        }
        if(bedrooms != null && bedrooms != 0) {
            predicates.add(builder.equal(root.get("bedrooms"), bedrooms));
        }
        if(swimmingPool != null && swimmingPool) {
            predicates.add(builder.equal(root.get("swimmingPool"), true));
        }
        if(parking != null && parking) {
            predicates.add(builder.equal(root.get("parking"), true));
        }
        if(price != null && price != 0) {
            predicates.add(builder.lessThanOrEqualTo(root.get("price"), price));
        }
        if(averageRating!=null)
            predicates.add(builder.greaterThanOrEqualTo(root.get("averageRating"), averageRating));

        if (!predicates.isEmpty()) {
            query.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
        }

        List<Property> properties = entityManager.createQuery(query).getResultList();
        List<PropertyDisplay> propertyDisplays = properties.stream()
                .map(PropertyConverter::propertyToPropertyDisplay)
                .toList();
        System.out.println(propertyDisplays);
        return propertyDisplays;
    }

}