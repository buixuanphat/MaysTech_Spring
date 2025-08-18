package com.bxp.MaysTech_Spring.controller;

import com.bxp.MaysTech_Spring.dto.ApiResponse;
import com.bxp.MaysTech_Spring.dto.delivery.*;
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


    @GetMapping("/deliveries/user/{userId}/{status}")
    public ApiResponse<List<DeliveryWithFirstProductResponse>> getDeliveryByUserIdAndStatus(@PathVariable int userId, @PathVariable String status) {
        ApiResponse<List<DeliveryWithFirstProductResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.OK.getCode());
        apiResponse.setMessage(MyApiResponse.OK.getMessage());
        apiResponse.setData(deliveryService.getDeliveryOfUserByStatus(userId, status));
        return apiResponse;
    }

    @GetMapping("/deliveries/all/{status}")
    public ApiResponse<List<DeliveryResponse>> getDeliveryByStatus(@PathVariable String status) {
        ApiResponse<List<DeliveryResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.OK.getCode());
        apiResponse.setMessage(MyApiResponse.OK.getMessage());
        apiResponse.setData(deliveryService.getDeliveryByStatus(status));
        return apiResponse;
    }

    @GetMapping("/deliveries/{id}")
    public ApiResponse<DeliveryResponse> getDeliveryById(@PathVariable int id) {
        ApiResponse<DeliveryResponse> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.OK.getCode());
        apiResponse.setMessage(MyApiResponse.OK.getMessage());
        apiResponse.setData(deliveryService.getDeliveryById(id));
        return apiResponse;
    }


    @PostMapping("/deliveries")
    public ApiResponse<DeliveryResponse> createDelivery(@RequestBody DeliveryCreateRequest request) {
        ApiResponse<DeliveryResponse> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.CREATED.getCode());
        apiResponse.setMessage(MyApiResponse.CREATED.getMessage());
        apiResponse.setData(deliveryService.createDelivery(request));
        return apiResponse;
    }


    @PatchMapping("/deliveries/{id}")
    public ApiResponse updateDelivery(@PathVariable int id, @Nullable @RequestParam String status) {
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.OK.getCode());
        apiResponse.setMessage(MyApiResponse.OK.getMessage());

        if(status==null){
            apiResponse.setData(deliveryService.updateFeedbackStatus(id));
            return apiResponse;
        }
        else
        {
            apiResponse.setData(deliveryService.updateStatus(id, status));
            return apiResponse;
        }
    }



    @GetMapping("/deliveries/best-selling")
    public ApiResponse<List<BestSellingResponse>> getBestSellingProducts() {
        ApiResponse<List<BestSellingResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.OK.getCode());
        apiResponse.setMessage(MyApiResponse.OK.getMessage());
        apiResponse.setData(deliveryService.getBestSellingProducts());
        return apiResponse;
    }


    @PatchMapping("deliveries/cancel/{id}")
    public ApiResponse<DeliveryResponse> cancel(@PathVariable int id)
    {
        ApiResponse<DeliveryResponse> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.OK.getCode());
        apiResponse.setMessage(MyApiResponse.OK.getMessage());
        apiResponse.setData(deliveryService.cancel(id));
        return apiResponse;
    }

    @PatchMapping("deliveries/cancel/request/{id}")
    public ApiResponse<DeliveryWithFirstProductResponse> requestCancel(@PathVariable int id)
    {
        ApiResponse<DeliveryWithFirstProductResponse> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.OK.getCode());
        apiResponse.setMessage(MyApiResponse.OK.getMessage());
        apiResponse.setData(deliveryService.requestCancelDelivery(id));
        return apiResponse;
    }

    @PatchMapping("deliveries/ignore/{id}")
    public ApiResponse<DeliveryResponse> ignore(@PathVariable int id)
    {
        ApiResponse<DeliveryResponse> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.OK.getCode());
        apiResponse.setMessage(MyApiResponse.OK.getMessage());
        apiResponse.setData(deliveryService.ignore(id));
        return apiResponse;
    }

    @GetMapping("/stats/product")
    public ApiResponse<List<ProductRevenue>> getProductRevenue()
    {
        ApiResponse<List<ProductRevenue>> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.OK.getCode());
        apiResponse.setMessage(MyApiResponse.OK.getMessage());
        apiResponse.setData(deliveryService.getProductRevenue());
        return apiResponse;
    }

    @GetMapping("/stats/daily")
    public ApiResponse<List<TimeRevenue>> getDailyRevenue()
    {
        ApiResponse<List<TimeRevenue>> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.OK.getCode());
        apiResponse.setMessage(MyApiResponse.OK.getMessage());
        apiResponse.setData(deliveryService.getDailyRevenue());
        return apiResponse;
    }

    @GetMapping("/stats/monthly")
    public ApiResponse<List<TimeRevenue>> getMonthlyRevenue()
    {
        ApiResponse<List<TimeRevenue>> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.OK.getCode());
        apiResponse.setMessage(MyApiResponse.OK.getMessage());
        apiResponse.setData(deliveryService.getMonthlyRevenue());
        return apiResponse;
    }

    @GetMapping("/stats/yearly")
    public ApiResponse<List<TimeRevenue>> getYearlyRevenue()
    {
        ApiResponse<List<TimeRevenue>> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.OK.getCode());
        apiResponse.setMessage(MyApiResponse.OK.getMessage());
        apiResponse.setData(deliveryService.getYearlyRevenue());
        return apiResponse;
    }





}
