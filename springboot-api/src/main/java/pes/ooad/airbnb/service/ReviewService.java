package pes.ooad.airbnb.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import pes.ooad.airbnb.converter.ReviewConverter;
import pes.ooad.airbnb.model.property.Property;
import pes.ooad.airbnb.model.review.Review;
import pes.ooad.airbnb.model.review.ReviewAdd;
import pes.ooad.airbnb.principal.CurrentUser;
import pes.ooad.airbnb.repository.PropertyRepository;
import pes.ooad.airbnb.repository.ReviewRepository;
import pes.ooad.airbnb.repository.UserRepository;

import java.util.stream.Stream;

@Service
public class ReviewService {
    private ReviewConverter reviewConverter = new ReviewConverter();
    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewRepository reviewRepository;

//    public Integer addReview(ReviewAdd reviewAdd) throws JsonProcessingException{
//        if(reviewRepository.findReviewByUserandProperty(CurrentUser.user_id, reviewAdd.getPropertyId()) != null){
//            return 0;
//        }
//        else{
//            Review review = reviewConverter.reviewAddToReview(reviewAdd);
//            review.setUser(userRepository.findById(CurrentUser.user_id).get());
//            reviewRepository.save(review);
//            return 1;
//        }
//    }

    public void addReview(ReviewAdd reviewAdd) throws JsonProcessingException{
        Review review = reviewConverter.reviewAddToReview(reviewAdd);
        review.setUser(userRepository.findById(CurrentUser.user_id).get());
        review.setProperty(propertyRepository.findById(reviewAdd.getPropertyId()).get());
        Property property = propertyRepository.findById(reviewAdd.getPropertyId()).get();
        if(property.getNumOfReviews()==0)
        {
            property.setAverageRating(Double.valueOf(reviewAdd.getRating()));
        }
        else{
            property.setAverageRating(Double.valueOf(property.getAverageRating()*property.getNumOfReviews() + reviewAdd.getRating())/Double.valueOf(property.getNumOfReviews()+1));
        }
        property.setNumOfReviews(property.getNumOfReviews()+1);
        reviewRepository.save(review);
    }
}