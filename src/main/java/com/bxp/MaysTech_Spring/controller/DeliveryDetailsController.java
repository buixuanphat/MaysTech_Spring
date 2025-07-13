package com.bxp.MaysTech_Spring.controller;

import com.bxp.MaysTech_Spring.dto.ApiResponse;
import com.bxp.MaysTech_Spring.dto.delivery_details.DeliveryDetailsCreateRequest;
import com.bxp.MaysTech_Spring.dto.delivery_details.DeliveryDetailsResponse;
import com.bxp.MaysTech_Spring.exception.MyApiResponse;
import com.bxp.MaysTech_Spring.service.DeliveryDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeliveryDetailsController {

    @Autowired
    DeliveryDetailsService deliveryDetailsService;

    @GetMapping("/delivery-details/{deliveryId}")
    public ApiResponse<List<DeliveryDetailsResponse>> getDeliveryDetails(@PathVariable("deliveryId") int deliveryId) {
        ApiResponse<List<DeliveryDetailsResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.OK.getCode());
        apiResponse.setMessage(MyApiResponse.OK.getMessage());
        apiResponse.setData(deliveryDetailsService.getDeliveringProduct(deliveryId));
        return apiResponse;
    }

    @PostMapping("/delivery-details/{deliveryId}")
    public ApiResponse<List<DeliveryDetailsResponse>> startDelivering(@PathVariable int deliveryId , @RequestBody List<DeliveryDetailsCreateRequest> requests)
    {
        ApiResponse<List<DeliveryDetailsResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.CREATED.getCode());
        apiResponse.setMessage(MyApiResponse.CREATED.getMessage());
        apiResponse.setData(deliveryDetailsService.addDeliveryDetails(deliveryId ,requests));
        return apiResponse;
    }

}
