package pes.ooad.airbnb.model.property;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pes.ooad.airbnb.model.review.Review;
import pes.ooad.airbnb.model.user.User;

import java.util.Hashtable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer property_id;
    private String propertyName;

    // -- Location --
    private String city;
    private String address;

    // -- Facilities --
    private Integer bedrooms;
    private Boolean swimmingPool;
    private Integer area;
    private Integer price;
    private Boolean parking;
    private Double Longitude;
    private Double Latitude;

    // -- Reviews --
    private Integer numOfReviews;
    private Double averageRating;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name ="user_id", nullable = false)
    private User host;

//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "review_id", nullable = false)
//    private List<Review> reviews;
}