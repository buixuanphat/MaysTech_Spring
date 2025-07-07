package com.bxp.MaysTech_Spring.controller;

import com.bxp.MaysTech_Spring.dto.ApiResponse;
import com.bxp.MaysTech_Spring.dto.product_image.ProductImageDTO;
import com.bxp.MaysTech_Spring.entity.ProductImage;
import com.bxp.MaysTech_Spring.exception.MyApiResponse;
import com.bxp.MaysTech_Spring.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductImageController {

    @Autowired
    ProductImageService productImageService;

    @GetMapping("/product-image/{prodId}")
    public ApiResponse<List<ProductImageDTO>> getImageByProduct(@PathVariable(value = "prodId") int productId)
    {
        ApiResponse<List<ProductImageDTO>> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.OK.getCode());
        apiResponse.setMessage(MyApiResponse.OK.getMessage());
        apiResponse.setData(productImageService.getImageByProduct(productId));
        return apiResponse;
    }
}
