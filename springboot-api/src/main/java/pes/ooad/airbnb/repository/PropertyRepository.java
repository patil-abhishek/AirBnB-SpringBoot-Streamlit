package pes.ooad.airbnb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pes.ooad.airbnb.model.property.Property;

public interface PropertyRepository extends JpaRepository<Property, Integer> {
    Property findPropertyByCity(String city);

//    @Query("Select p from Property p where p.city = ?1 and where p.swimmingPool = ?2")
//    Property findPropertyByCityAndSwimmingPool(String city, Boolean swimmingPool);
//
//    @Query("Select p from Property p where p.city = ?1 and where p.parking = ?2")
//    Property findPropertyByCityAndParking(String city, Boolean parking);
//
//    @Query("Select p from Property p where p.city = ?1 and where p.bedrooms = ?2")
//    Property findPropertyByCityAndBedrooms(String city, Integer bedrooms);
//
//    @Query("Select p from Property p where p.city = ?1 and where p.price <= ?2")
//    Property findPropertyByCityAndPrice(String city, Integer price);
//
//    @Query("Select p from Property p where p.city = ?1 and where p.averageRating >= ?2")
//    Property findPropertyByCityAndAverageRating(String city, Integer averageRating);
//
//    @Query("Select p from Property p where p.city = ?1 and where p.address = ?2")
//    Property findPropertyByCityAndAddress(String city, String address);
//
//    @Query("Select p from Property p where p.city = ?1 and where p.address = ?2 and where p.swimmingPool = ?3")
//    Property findPropertyByCityAndAddressAndSwimmingPool(String city, String address, Boolean swimmingPool);
//
//    @Query("Select p from Property p where p.city = ?1 and where p.address = ?2 and where p.parking = ?3")
//    Property findPropertyByCityAndAddressAndParking(String city, String address, Boolean parking);
//
//    @Query("Select p from Property p where p.city = ?1 and where p.address = ?2 and where p.bedrooms = ?3")
//    Property findPropertyByCityAndAddressAndBedrooms(String city, String address, Integer bedrooms);
//
//    @Query("Select p from Property p where p.city = ?1 and where p.address = ?2 and where p.price <= ?3")
//    Property findPropertyByCityAndAddressAndPrice(String city, String address, Integer price);
//
//    @Query("Select p from Property p where p.city = ?1 and where p.address = ?2 and where p.averageRating >= ?3")
//    Property findPropertyByCityAndAddressAndAverageRating(String city, String address, Integer averageRating);
//
//    @Query("Select p from Property p where p.city = ?1 and where p.address = ?2 and where p.swimmingPool = ?3 and where p.parking = ?4")
//    Property findPropertyByCityAndAddressAndSwimmingPoolAndParking(String city, String address, Boolean swimmingPool, Boolean parking);
//
//    @Query("Select p from Property p where p.city = ?1 and where p.address = ?2 and where p.swimmingPool = ?3 and where p.bedrooms = ?4")
//    Property findPropertyByCityAndAddressAndSwimmingPoolAndBedrooms(String city, String address, Boolean swimmingPool, Integer bedrooms);
//
//    @Query("Select p from Property p where p.city = ?1 and where p.address = ?2 and where p.swimmingPool = ?3 and where p.price <= ?4")
//    Property findPropertyByCityAndAddressAndSwimmingPoolAndPrice(String city, String address, Boolean swimmingPool, Integer price);
//
//    @Query("Select p from Property p where p.city = ?1 and where p.address = ?2 and where p.swimmingPool = ?3 and where p.averageRating >= ?4")
//    Property findPropertyByCityAndAddressAndSwimmingPoolAndAverageRating(String city, String address, Boolean swimmingPool, Integer averageRating);
//
//    @Query("Select p from Property p where p.city = ?1 and where p.address = ?2 and where p.parking = ?3 and where p.bedrooms = ?4")
//    Property findPropertyByCityAndAddressAndParkingAndBedrooms(String city, String address, Boolean parking, Integer bedrooms);
//
//    @Query("Select p from Property p where p.city = ?1 and where p.address = ?2 and where p.parking = ?3 and where p.price <= ?4")
//    Property findPropertyByCityAndAddressAndParkingAndPrice(String city, String address, Boolean parking, Integer price);
//
//    @Query("Select p from Property p where p.city = ?1 and where p.address = ?2 and where p.parking = ?3 and where p.averageRating >= ?4")
//    Property findPropertyByCityAndAddressAndParkingAndAverageRating(String city, String address, Boolean parking, Integer averageRating);
//
//    @Query("Select p from Property p where p.city = ?1 and where p.address = ?2 and where p.bedrooms = ?3 and where p.price <= ?4")
//    Property findPropertyByCityAndAddressAndBedroomsAndPrice(String city, String address, Integer bedrooms, Integer price);
//
//    @Query("Select p from Property p where p.city = ?1 and where p.address = ?2 and where p.bedrooms = ?3 and where p.averageRating >= ?4")
//    Property findPropertyByCityAndAddressAndBedroomsAndAverageRating(String city, String address, Integer bedrooms, Integer averageRating);
//
//    @Query("Select p from Property p where p.city = ?1 and where p.address = ?2 and where p.price <= ?3 and where p.averageRating >= ?4")
//    Property findPropertyByCityAndAddressAndPriceAndAverageRating(String city, String address, Integer price, Integer averageRating);
//
//    @Query("Select p from Property p where p.city = ?1 and where p.swimmingPool = ?2 and where p.parking = ?3")
//    Property findPropertyByCityAndSwimmingPoolAndParking(String city, Boolean swimmingPool, Boolean parking);
//
//    @Query("Select p from Property p where p.city = ?1 and where p.swimmingPool = ?2 and where p.bedrooms = ?3")
//    Property findPropertyByCityAndSwimmingPoolAndBedrooms(String city, Boolean swimmingPool, Integer bedrooms);



}
