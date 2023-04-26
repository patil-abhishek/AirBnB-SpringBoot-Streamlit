package pes.ooad.airbnb.model.booking;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.util.http.parser.Host;
import pes.ooad.airbnb.model.property.Property;
import pes.ooad.airbnb.model.user.User;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer booking_id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name ="user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name ="property_id", nullable = false)
    private Property property;

    private LocalDate checkinDate;
    private LocalDate checkoutDate;
    private Integer numOfGuests;
    private Integer totalPrice;
}
