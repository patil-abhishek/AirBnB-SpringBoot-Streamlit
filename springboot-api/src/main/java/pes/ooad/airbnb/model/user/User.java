package pes.ooad.airbnb.model.user;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import pes.ooad.airbnb.model.property.Property;
import pes.ooad.airbnb.model.review.Review;

import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;
    private String firstname;
    private String lastname;
    private Integer age;
    private String phone;
    private String email;
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "host" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Property> properties;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_property_favorite",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "property_id")
    )
    private Set<Property> favoriteProperties;

//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "review_id", nullable = false)
//    private List<Review> reviews;

}
