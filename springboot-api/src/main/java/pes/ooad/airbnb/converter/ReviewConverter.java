package pes.ooad.airbnb.converter;

import pes.ooad.airbnb.model.review.Review;
import pes.ooad.airbnb.model.review.ReviewAdd;
import pes.ooad.airbnb.model.review.ReviewDisplay;
import pes.ooad.airbnb.principal.CurrentUser;
import pes.ooad.airbnb.repository.PropertyRepository;
import pes.ooad.airbnb.repository.UserRepository;

import java.util.List;

public class ReviewConverter {
    public Review reviewAddToReview(ReviewAdd reviewAdd) {
        Review review = new Review();
        review.setComment(reviewAdd.getComment());
        review.setRating(reviewAdd.getRating());
        return review;
    }

    public static ReviewDisplay reviewToReviewDisplay(Review review) {
        ReviewDisplay reviewDisplay = new ReviewDisplay();
        reviewDisplay.setComment(review.getComment());
        reviewDisplay.setRating(review.getRating());
        reviewDisplay.setName(review.getUser().getFirstname() + " " + review.getUser().getLastname());
        return reviewDisplay;
    }

    public static List<ReviewDisplay> reviewsToReviewDisplays(List<Review> reviews) {
        List<ReviewDisplay> reviewDisplays = null;
        for(Review review : reviews) {
            ReviewDisplay reviewDisplay = reviewToReviewDisplay(review);
            reviewDisplays.add(reviewDisplay);
        }
        return reviewDisplays;
    }
}
