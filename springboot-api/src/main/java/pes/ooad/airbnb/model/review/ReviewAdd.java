package pes.ooad.airbnb.model.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewAdd {
    private Integer rating;
    private String comment;
    private Integer propertyId;
}