package pes.ooad.airbnb.model.property;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PropertyAdd {
    private String propertyName;

    // -- Location --
    private String city;
    private String address;
    private Double Longitude;
    private Double Latitude;

    // -- Facilities --
    private Integer bedrooms;
    private Integer area;
    private Boolean swimmingPool;
    private Boolean parking;
    private Integer price;
}
