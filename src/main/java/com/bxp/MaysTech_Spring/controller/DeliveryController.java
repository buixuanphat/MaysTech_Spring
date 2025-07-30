package com.bxp.MaysTech_Spring.controller;

import com.bxp.MaysTech_Spring.dto.ApiResponse;
import com.bxp.MaysTech_Spring.dto.delivery.DeliveryCreateRequest;
import com.bxp.MaysTech_Spring.dto.delivery.DeliveryResponse;
import com.bxp.MaysTech_Spring.exception.MyApiResponse;
import com.bxp.MaysTech_Spring.service.DeliveryService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeliveryController {

    @Autowired
    DeliveryService deliveryService;

    @GetMapping("/deliveries/{userId}")
    public ApiResponse<List<DeliveryResponse>> getDeliveriesList(@PathVariable int userId, @Nullable @RequestParam String status)
    {
        ApiResponse<List<DeliveryResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.OK.getCode());
        apiResponse.setMessage(MyApiResponse.OK.getMessage());

        if(status!=null)
        {
            apiResponse.setData(deliveryService.getDeliveryByStatus(userId,status));
            return apiResponse;
        }

        apiResponse.setData(deliveryService.getDeliveriesByUserId(userId));
        return apiResponse;
    }

    @PostMapping("/deliveries")
    public ApiResponse<DeliveryResponse> createDelivery(@RequestBody DeliveryCreateRequest request)
    {
        ApiResponse<DeliveryResponse>  apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.CREATED.getCode());
        apiResponse.setMessage(MyApiResponse.CREATED.getMessage());
        apiResponse.setData(deliveryService.createDelivery(request));
        return apiResponse;
    }

    @PatchMapping("/deliveries/{id}")
    public ApiResponse<DeliveryResponse> updateFeedbackStatus(@PathVariable int id)
    {
        ApiResponse<DeliveryResponse>  apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.OK.getCode());
        apiResponse.setMessage(MyApiResponse.OK.getMessage());
        apiResponse.setData(deliveryService.updateFeedbackStatus(id));
        return apiResponse;
    }



}
