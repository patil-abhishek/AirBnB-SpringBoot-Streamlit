package pes.ooad.airbnb.model.review;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewParameters {
    private Integer userId;
    private Integer propertyId;
    private Integer rating;
    private String comment;

}
