package com.bxp.MaysTech_Spring.service;

import com.bxp.MaysTech_Spring.dto.rating.CreateRatingRequest;
import com.bxp.MaysTech_Spring.dto.rating.RatingResponse;
import com.bxp.MaysTech_Spring.entity.Product;
import com.bxp.MaysTech_Spring.entity.Rating;
import com.bxp.MaysTech_Spring.entity.User;
import com.bxp.MaysTech_Spring.repository.ProductRepository;
import com.bxp.MaysTech_Spring.repository.RatingRepository;
import com.bxp.MaysTech_Spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    public RatingResponse createRating(CreateRatingRequest request) {
        Rating rating = new Rating();

        User user = userRepository.getById(request.getUserId());
        Product product = productRepository.getById(request.getProductId());

        rating.setUser(user);
        rating.setProduct(product);
        rating.setRating(request.getRating());

        return convertEntityToDTO(ratingRepository.save(rating));
    }

    public double getRatingsOfProduct(int productId) {
        double avgRating;
        List<Rating> responses = ratingRepository.findAllByProduct_Id(productId);
        avgRating = responses.stream().mapToDouble(Rating::getRating).sum();
        return avgRating/responses.size();
    }

    RatingResponse convertEntityToDTO(Rating rating) {
        RatingResponse response = new RatingResponse();
        response.setId(rating.getId());
        response.setUserId(rating.getUser().getId());
        response.setProductId(rating.getProduct().getId());
        response.setRating(rating.getRating());
        return response;
    }

}
