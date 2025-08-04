package com.bxp.MaysTech_Spring.controller;

import com.bxp.MaysTech_Spring.dto.ApiResponse;
import com.bxp.MaysTech_Spring.dto.product_highlight.ProductHighlightResponse;
import com.bxp.MaysTech_Spring.exception.MyApiResponse;
import com.bxp.MaysTech_Spring.service.ProductHighlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
