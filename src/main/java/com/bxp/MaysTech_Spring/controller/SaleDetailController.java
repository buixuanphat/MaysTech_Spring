package com.bxp.MaysTech_Spring.controller;

import com.bxp.MaysTech_Spring.dto.ApiResponse;
import com.bxp.MaysTech_Spring.dto.sale_detail.SaleDetailRequest;
import com.bxp.MaysTech_Spring.dto.sale_detail.SaleDetailResponse;
import com.bxp.MaysTech_Spring.exception.MyApiResponse;
import com.bxp.MaysTech_Spring.service.SaleDetailService;
import com.bxp.MaysTech_Spring.service.SaleService;
import jakarta.servlet.Servlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SaleDetailController {
    @Autowired
    SaleDetailService  saleDetailService;

    @PostMapping("/sale-details")
    public ApiResponse<SaleDetailResponse> createSaleDetail(@RequestBody SaleDetailRequest saleDetailRequest){
        ApiResponse<SaleDetailResponse> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.CREATED.getCode());
        apiResponse.setMessage(MyApiResponse.CREATED.getMessage());
        apiResponse.setData(saleDetailService.createSaleDetail(saleDetailRequest));
        return apiResponse;
    }

    @DeleteMapping("sale-details/{id}")
    public ApiResponse<SaleDetailResponse> deleteSaleDetail(@PathVariable int id){
        ApiResponse<SaleDetailResponse> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.NO_CONTENT.getCode());
        apiResponse.setMessage(MyApiResponse.NO_CONTENT.getMessage());
        apiResponse.setData(saleDetailService.deleteSaleDetail(id));
        return apiResponse;
    }

    @GetMapping("/sale-details/{id}")
    public ApiResponse<List<SaleDetailResponse>> getAllDetailOfSale(@PathVariable int id){
        ApiResponse<List<SaleDetailResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.OK.getCode());
        apiResponse.setMessage(MyApiResponse.OK.getMessage());
        apiResponse.setData(saleDetailService.getAllDetailOfSale(id));
        return apiResponse;
    }
}
