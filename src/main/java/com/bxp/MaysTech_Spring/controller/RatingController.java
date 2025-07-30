package com.bxp.MaysTech_Spring.controller;

import com.bxp.MaysTech_Spring.dto.ApiResponse;
import com.bxp.MaysTech_Spring.dto.rating.CreateRatingRequest;
import com.bxp.MaysTech_Spring.dto.rating.RatingResponse;
import com.bxp.MaysTech_Spring.entity.Rating;
import com.bxp.MaysTech_Spring.exception.MyApiResponse;
import com.bxp.MaysTech_Spring.repository.RatingRepository;
import com.bxp.MaysTech_Spring.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RatingController {
    @Autowired
    RatingService ratingService;

    @PostMapping("/ratings")
    public ApiResponse<RatingResponse> createRating(@RequestBody CreateRatingRequest request){
        ApiResponse<RatingResponse> response = new ApiResponse<>();
        response.setStatusCode(MyApiResponse.CREATED.getCode());
        response.setMessage(MyApiResponse.CREATED.getMessage());
        response.setData(ratingService.createRating(request));
        return response;
    }

    @GetMapping("/ratings/{prodId}")
    public ApiResponse<Double> getRatingsOfProduct(@PathVariable("prodId") int prodId)
    {
        ApiResponse<Double> response = new ApiResponse<>();
        response.setStatusCode(MyApiResponse.OK.getCode());
        response.setMessage(MyApiResponse.OK.getMessage());
        response.setData(ratingService.getRatingsOfProduct(prodId));
        return response;
    }
}
