package pes.ooad.airbnb.model.property;

import lombok.Getter;
import pes.ooad.airbnb.model.image.Image;
import java.util.List;


public class SearchQuery {
    public String city;
    public String address;
    public Integer bedrooms;
    public Boolean swimmingPool;
    public Boolean parking;
    public Integer price;
    public Double averageRating;
}
