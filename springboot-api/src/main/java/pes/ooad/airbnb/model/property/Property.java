package pes.ooad.airbnb.model.property;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pes.ooad.airbnb.model.image.Image;
import pes.ooad.airbnb.model.user.User;
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

    // -- Reviews --
    private Integer numOfReviews;
    private Double averageRating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="user_id", nullable = false)
    private User host;

    @OneToMany(mappedBy = "image_id" ,fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Image> images;

}