package pes.ooad.airbnb.converter;

import pes.ooad.airbnb.model.booking.Booking;
import pes.ooad.airbnb.model.booking.BookingAdd;
import pes.ooad.airbnb.model.booking.BookingDisplay;
import pes.ooad.airbnb.principal.CurrentUser;
import pes.ooad.airbnb.repository.PropertyRepository;
import pes.ooad.airbnb.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class BookingConverter {
    public static Booking bookingAddToBooking(BookingAdd bookingAdd) {
        Booking booking = new Booking();
        booking.setCheckinDate(bookingAdd.getCheckinDate());
        booking.setCheckoutDate(bookingAdd.getCheckoutDate());
        booking.setNumOfGuests(bookingAdd.getNumOfGuests());
        booking.setTotalPrice(bookingAdd.getPrice());
        return booking;
    }

    public static BookingDisplay bookingToBookingDisplay(Booking booking) {
        BookingDisplay bookingDisplay = new BookingDisplay();
        bookingDisplay.setPropertyId(booking.getProperty().getProperty_id());
        bookingDisplay.setPropertyName(booking.getProperty().getPropertyName());
        bookingDisplay.setAddress(booking.getProperty().getAddress());
        bookingDisplay.setCity(booking.getProperty().getCity());
        bookingDisplay.setHostName(booking.getProperty().getHost().getFirstname()+ " " + booking.getProperty().getHost().getLastname());
        bookingDisplay.setHostPhone(booking.getProperty().getHost().getPhone());
        bookingDisplay.setCheckinDate(booking.getCheckinDate());
        bookingDisplay.setCheckoutDate(booking.getCheckoutDate());
        bookingDisplay.setNumOfGuests(booking.getNumOfGuests());
        bookingDisplay.setTotalPrice(booking.getTotalPrice());
        return bookingDisplay;
    }

    public static List<BookingDisplay> bookingsToBookingDisplays(List<Booking> bookings) {
        List<BookingDisplay> bookingDisplays = new ArrayList<BookingDisplay>();
        for(Booking booking : bookings) {
            BookingDisplay bookingDisplay = bookingToBookingDisplay(booking);
            bookingDisplays.add(bookingDisplay);
        }
        return bookingDisplays;
    }

}
