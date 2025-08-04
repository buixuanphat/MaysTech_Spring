package com.bxp.MaysTech_Spring.controller;

import com.bxp.MaysTech_Spring.dto.ApiResponse;
import com.bxp.MaysTech_Spring.dto.product_image.ProductImageResponse;
import com.bxp.MaysTech_Spring.exception.MyApiResponse;
import com.bxp.MaysTech_Spring.service.ProductImageService;
import com.sun.jdi.VoidType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ProductImageController {

    @Autowired
    ProductImageService productImageService;

    @GetMapping("/product-image/{prodId}")
    public ApiResponse<List<ProductImageResponse>> getImageByProduct(@PathVariable(value = "prodId") int productId)
    {
        ApiResponse<List<ProductImageResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.OK.getCode());
        apiResponse.setMessage(MyApiResponse.OK.getMessage());
        apiResponse.setData(productImageService.getImageByProduct(productId));
        return apiResponse;
    }

    @DeleteMapping("/product-image/{id}")
    public ApiResponse<Void> deleteImageOfProduct(@PathVariable("id") int id) {
        ApiResponse<Void> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.NO_CONTENT.getCode());
        apiResponse.setMessage(MyApiResponse.NO_CONTENT.getMessage());

        productImageService.deleteImageOfProduct(id);

        apiResponse.setData(null);
        return apiResponse;
    }

    @PostMapping("/product-image")
    public ApiResponse<ProductImageResponse> addProductImage(@RequestBody Map<String, String> body)
    {
        ApiResponse<ProductImageResponse> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.CREATED.getCode());
        apiResponse.setMessage(MyApiResponse.CREATED.getMessage());
        apiResponse.setData(productImageService.addImageOfProduct(body));
        return apiResponse;
    }


}
