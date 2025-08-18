package com.bxp.MaysTech_Spring.controller;

import com.bxp.MaysTech_Spring.dto.ApiResponse;
import com.bxp.MaysTech_Spring.dto.product_highlight.ProductHighlightCreateRequest;
import com.bxp.MaysTech_Spring.dto.product_highlight.ProductHighlightResponse;
import com.bxp.MaysTech_Spring.exception.MyApiResponse;
import com.bxp.MaysTech_Spring.service.ProductHighlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductHighlightController {

    @Autowired
    private ProductHighlightService productHighlightService;

    @GetMapping("/product-highlight")
    public ApiResponse<List<ProductHighlightResponse>> getProductHighlight() {
        ApiResponse<List<ProductHighlightResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.OK.getCode());
        apiResponse.setMessage(MyApiResponse.OK.getMessage());
        apiResponse.setData(productHighlightService.getProductHighlight());
        return apiResponse;
    }

    @PostMapping("/product-highlight")
    public ApiResponse<ProductHighlightResponse> addProductHighLight(@RequestBody ProductHighlightCreateRequest request)
    {
        ApiResponse<ProductHighlightResponse> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.CREATED.getCode());
        apiResponse.setMessage(MyApiResponse.CREATED.getMessage());
        apiResponse.setData(productHighlightService.addProductHighLight(request));
        return apiResponse;
    }


    @DeleteMapping("/product-highlight/{id}")
    public ApiResponse<Void> delete(@PathVariable int id)
    {
        ApiResponse<Void> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.NO_CONTENT.getCode());
        apiResponse.setMessage(MyApiResponse.NO_CONTENT.getMessage());
        productHighlightService.deleteProductHighLight(id);
        return apiResponse;
    }



}
