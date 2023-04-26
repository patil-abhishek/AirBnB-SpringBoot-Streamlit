package pes.ooad.airbnb.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import pes.ooad.airbnb.converter.BookingConverter;
import pes.ooad.airbnb.model.booking.Booking;
import pes.ooad.airbnb.model.booking.BookingAdd;
import pes.ooad.airbnb.model.booking.BookingDisplay;
import pes.ooad.airbnb.model.property.Property;
import pes.ooad.airbnb.principal.CurrentUser;
import pes.ooad.airbnb.repository.BookingRepository;
import pes.ooad.airbnb.repository.PropertyRepository;
import pes.ooad.airbnb.repository.UserRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PropertyService propertyService;

    public List<BookingDisplay> getBookings() throws JsonProcessingException {
        return BookingConverter.bookingsToBookingDisplays((List<Booking>) bookingRepository.findAllByUser(userRepository.findById(CurrentUser.user_id).get()));
    }

    private static Integer calculateTotalPrice(Integer pricePerNight, LocalDate checkinDate, LocalDate checkoutDate) {
        int numOfNights = (int) ChronoUnit.DAYS.between(checkinDate, checkoutDate);
        return numOfNights * pricePerNight;
    }

    public void addBooking(BookingAdd bookingAdd) throws JsonProcessingException {
        LocalDate checkinDate = bookingAdd.getCheckinDate();
        LocalDate checkoutDate = bookingAdd.getCheckoutDate();
        List<Booking> propertyBookings = bookingRepository.findAll();

        // Check for overlapping bookings
        for (Booking existingBooking : propertyBookings) {
            LocalDate existingCheckin = existingBooking.getCheckinDate();
            LocalDate existingCheckout = existingBooking.getCheckoutDate();

            if ((checkinDate.isAfter(existingCheckin) && checkinDate.isBefore(existingCheckout))
                    || (checkoutDate.isAfter(existingCheckin) && checkoutDate.isBefore(existingCheckout))
                    || (checkinDate.isEqual(existingCheckin) || checkoutDate.isEqual(existingCheckout))) {
                throw new RuntimeException("This property is already booked for the selected period : "+existingCheckin+" to "+existingCheckout);
            }
        }

        Booking booking = BookingConverter.bookingAddToBooking(bookingAdd);
        booking.setProperty(propertyRepository.findById(bookingAdd.getPropertyId()).get());
        booking.setUser(userRepository.findById(CurrentUser.user_id).get());
        booking.setTotalPrice(calculateTotalPrice(propertyRepository.findById(bookingAdd.getPropertyId()).get().getPrice(), bookingAdd.getCheckinDate(), bookingAdd.getCheckoutDate()));
        bookingRepository.save(booking);
    }

//    public List<Integer> ageAnalysis() {
//        @Query("SELECT age FROM Booking b WHERE b.user.age BETWEEN 0 AND 18")
//        List<Booking> bookings;
//    }
}
