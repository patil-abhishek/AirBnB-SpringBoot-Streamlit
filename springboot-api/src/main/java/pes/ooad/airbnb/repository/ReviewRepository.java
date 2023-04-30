package pes.ooad.airbnb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pes.ooad.airbnb.model.property.Property;
import pes.ooad.airbnb.model.review.Review;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ReviewRepository extends JpaRepository<Review, Integer>{

//    @Query("Select r from Review r WHERE (r.user = ?1) AND (r.property_id = ?2)")
//    Property findReviewByUserandProperty(Integer user_id, Integer property_id);

//    @Query("Select r from Review r where r.property_id = ?1")
//    List<Review> findByPropertyId(Integer property_id);
}
