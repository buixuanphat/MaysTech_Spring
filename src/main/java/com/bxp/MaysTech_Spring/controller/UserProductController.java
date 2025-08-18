package com.bxp.MaysTech_Spring.controller;

import com.bxp.MaysTech_Spring.dto.ApiResponse;
import com.bxp.MaysTech_Spring.dto.user_product.UserProductCreateRequest;
import com.bxp.MaysTech_Spring.dto.user_product.UserProductResponse;
import com.bxp.MaysTech_Spring.dto.user_product.UserProductTotalResponse;
import com.bxp.MaysTech_Spring.exception.MyApiResponse;
import com.bxp.MaysTech_Spring.service.UserProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserProductController {

    @Autowired
    UserProductService userProductService;

    @PostMapping("/user-product")
    public ApiResponse<UserProductResponse> addProductToCart(@RequestBody UserProductCreateRequest userProductRequest){
        ApiResponse<UserProductResponse> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.CREATED.getCode());
        apiResponse.setMessage(MyApiResponse.CREATED.getMessage());
        apiResponse.setData(userProductService.addProductToCart(userProductRequest));
        return apiResponse;
    }

    @GetMapping("/user-product/{userId}")
    public ApiResponse<List<UserProductResponse>> getProductInCart(@PathVariable int userId)
    {
        ApiResponse<List<UserProductResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.OK.getCode());
        apiResponse.setMessage(MyApiResponse.OK.getMessage());
        apiResponse.setData(userProductService.getProductInCart(userId));
        return apiResponse;
    }

    @DeleteMapping("/user-product/{id}")
    public ApiResponse removeProductFromCart(@PathVariable int id)
    {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatusCode(MyApiResponse.NO_CONTENT.getCode());
        apiResponse.setMessage(MyApiResponse.NO_CONTENT.getMessage());

        userProductService.deleteProductFromCart(id);
        return apiResponse;
    }

    @PatchMapping("/user-product/{id}")
    public ApiResponse<UserProductResponse> choose (@PathVariable("id") int id, @RequestParam("isChosen") boolean isChosen)
    {
        ApiResponse<UserProductResponse> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.OK.getCode());
        apiResponse.setMessage(MyApiResponse.OK.getMessage());
        apiResponse.setData(userProductService.chosen(id, isChosen));
        return apiResponse;
    }

    @GetMapping("/user-product/total/{userId}")
    public ApiResponse<UserProductTotalResponse> getTotalCart(@PathVariable int userId)
    {
        ApiResponse<UserProductTotalResponse> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.OK.getCode());
        apiResponse.setMessage(MyApiResponse.OK.getMessage());
        apiResponse.setData(userProductService.getCartTotalByUserId(userId));
        return apiResponse;
    }

    @DeleteMapping("/user-product/delete/{userId}")
    public ApiResponse<Void> deleteSelected(@PathVariable int userId)
    {
        ApiResponse<Void> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.NO_CONTENT.getCode());
        apiResponse.setMessage(MyApiResponse.NO_CONTENT.getMessage());
        userProductService.deleteSelected(userId);
        return apiResponse;
    }




}
