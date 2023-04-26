package pes.ooad.airbnb.model.booking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingAdd {
    private Integer propertyId;
    private LocalDate checkinDate;
    private LocalDate checkoutDate;
    private Integer numOfGuests;
    private Integer price;
}
