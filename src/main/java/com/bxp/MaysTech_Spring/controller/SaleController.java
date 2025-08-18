package com.bxp.MaysTech_Spring.controller;

import com.bxp.MaysTech_Spring.dto.ApiResponse;
import com.bxp.MaysTech_Spring.dto.sale.SaleResponse;
import com.bxp.MaysTech_Spring.entity.Sale;
import com.bxp.MaysTech_Spring.exception.MyApiResponse;
import com.bxp.MaysTech_Spring.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class SaleController {
    @Autowired
    SaleService saleService;

    @GetMapping("sales/{id}")
    public ApiResponse<SaleResponse> getSaleById(@PathVariable int id){
        ApiResponse<SaleResponse> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.OK.getCode());
        apiResponse.setMessage(MyApiResponse.OK.getMessage());
        apiResponse.setData(saleService.getSaleById(id));
        return apiResponse;
    }

    @PostMapping("/sales")
    public ApiResponse<SaleResponse> createSale(@RequestBody Map<String, Double> percent){
        ApiResponse<SaleResponse> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.CREATED.getCode());
        apiResponse.setMessage(MyApiResponse.CREATED.getMessage());
        apiResponse.setData(saleService.createSale(percent.get("percent")));
        return apiResponse;
    }

    @PatchMapping("sales/{id}")
    public ApiResponse<SaleResponse> updateSale(@PathVariable int id , @RequestBody Map<String, Double> percent){
        ApiResponse<SaleResponse> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.OK.getCode());
        apiResponse.setMessage(MyApiResponse.OK.getMessage());
        apiResponse.setData(saleService.updateSale(id, percent.get("percent")));
        return apiResponse;
    }

    @GetMapping("/sales")
    public ApiResponse<List<SaleResponse>> getAllSales(){
        ApiResponse<List<SaleResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.OK.getCode());
        apiResponse.setMessage(MyApiResponse.OK.getMessage());
        apiResponse.setData(saleService.getAllSales());
        return apiResponse;
    }

    @DeleteMapping("sales/{id}")
    public ApiResponse<SaleResponse> deleteSale(@PathVariable int id){
        ApiResponse<SaleResponse> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.NO_CONTENT.getCode());
        apiResponse.setMessage(MyApiResponse.NO_CONTENT.getMessage());
        apiResponse.setData(saleService.deleteSale(id));
        return apiResponse;
    }
}
