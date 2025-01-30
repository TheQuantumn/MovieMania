package dev.priyanshu.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Review createReview(String reviewBody, String imdbId) {
        // Insert the review into the "reviews" collection
        Review review = reviewRepository.insert(new Review(reviewBody));

        // Find the movie document by imdbId
        Movie movie = mongoTemplate.findOne(
                Query.query(Criteria.where("imdbId").is(imdbId)),
                Movie.class
        );

        if (movie != null) {
            // Push the review to the reviewIds field
            mongoTemplate.update(Movie.class)
                    .matching(Criteria.where("imdbId").is(imdbId))
                    .apply(new Update().push("reviewIds").value(review))
                    .first();
        } else {
            // Handle case when the movie is not found (optional)
            System.out.println("Movie with imdbId " + imdbId + " not found!");
        }

        return review;
    }

}
