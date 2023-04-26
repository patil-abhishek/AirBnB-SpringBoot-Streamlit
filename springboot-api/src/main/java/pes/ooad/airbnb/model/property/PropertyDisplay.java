package pes.ooad.airbnb.model.property;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pes.ooad.airbnb.model.review.Review;
import pes.ooad.airbnb.model.review.ReviewDisplay;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
    private Double Longitude;
    private Double Latitude;
    private String hostName;
    private String hostPhone;
    private String hostEmail;
    private Double averageRating;
    private List<ReviewDisplay> reviewDisplay;
}
