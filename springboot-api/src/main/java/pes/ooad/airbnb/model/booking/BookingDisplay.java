package pes.ooad.airbnb.model.booking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BookingDisplay {
    private Integer propertyId;
    private String propertyName;
    private String city;
    private String address;
    private String hostName;
    private String hostPhone;
    private LocalDate checkinDate;
    private LocalDate checkoutDate;
    private Integer numOfGuests;
    private Integer totalPrice;
}
