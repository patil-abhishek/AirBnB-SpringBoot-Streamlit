package pes.ooad.airbnb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pes.ooad.airbnb.model.booking.Booking;
import pes.ooad.airbnb.model.property.Property;
import pes.ooad.airbnb.model.user.User;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findAllByUser(User user);
    List<Booking> findByProperty(Property property);

}