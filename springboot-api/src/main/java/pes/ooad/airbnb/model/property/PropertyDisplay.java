package pes.ooad.airbnb.model.property;

import lombok.Getter;
import lombok.Setter;
import pes.ooad.airbnb.model.image.Image;

import java.util.List;

@Getter
@Setter
public class PropertyDisplay {
    private Integer property_id;
    private String propertyName;
    private String city;
    private String address;
    private Integer bedrooms;
    private Integer area;
    private Boolean swimmingPool;
    private Boolean parking;
    private Integer price;
    private String hostName;
    private String hostPhone;
    private String hostEmail;
    private List<Image> images;
    private Double averageRating;
    private Integer numOfReviews;
}
