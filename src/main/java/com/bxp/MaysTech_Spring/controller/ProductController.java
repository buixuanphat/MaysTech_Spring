package com.bxp.MaysTech_Spring.controller;

import com.bxp.MaysTech_Spring.dto.ApiResponse;
import com.bxp.MaysTech_Spring.dto.product.ProductRequest;
import com.bxp.MaysTech_Spring.dto.product.ProductResponse;
import com.bxp.MaysTech_Spring.exception.MyApiResponse;
import com.bxp.MaysTech_Spring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/products")
    public ApiResponse<ProductResponse> addProduct(@RequestBody ProductRequest request)
    {
        ApiResponse<ProductResponse> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.CREATED.getCode());
        apiResponse.setMessage(MyApiResponse.CREATED.getMessage());
        apiResponse.setData(productService.createProduct(request));
        return apiResponse;
    }

    @GetMapping("/products")
    public ApiResponse getProducts()
    {
        ApiResponse<List<ProductResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.OK.getCode());
        apiResponse.setMessage(MyApiResponse.OK.getMessage());
        apiResponse.setData(productService.getProducts());
        return apiResponse;
    }

    @GetMapping("/products/{prodId}")
    public ApiResponse getProductsById(@PathVariable int prodId)
    {
        ApiResponse<ProductResponse> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.OK.getCode());
        apiResponse.setMessage(MyApiResponse.OK.getMessage());
        apiResponse.setData(productService.getProductById(prodId));
        return apiResponse;
    }

    @PutMapping("/products/{prodId}")
    public ApiResponse<ProductResponse> updateProduct(@PathVariable int prodId, @RequestBody ProductRequest request)
    {
        ApiResponse<ProductResponse> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.OK.getCode());
        apiResponse.setMessage(MyApiResponse.OK.getMessage());
        apiResponse.setData(productService.updateProduct(prodId, request));
        return apiResponse;
    }

    @DeleteMapping("/products/{prodId}")
    public ApiResponse deleteProduct(@PathVariable int prodId)
    {
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.NO_CONTENT.getCode());
        apiResponse.setMessage(MyApiResponse.NO_CONTENT.getMessage());
        productService.deleteProduct(prodId);
        return apiResponse;
    }

    @GetMapping("/products/category/{catId}")
    public ApiResponse<List<ProductResponse>> getProductsByCategory(@PathVariable int catId)
    {
        ApiResponse<List<ProductResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.OK.getCode());
        apiResponse.setMessage(MyApiResponse.OK.getMessage());
        apiResponse.setData(productService.getProductsByCategory(catId));
        return apiResponse;
    }

    @GetMapping("/products/category/{catId}/brand/{brandId}")
    public ApiResponse<List<ProductResponse>> getProductsByCategoryAndBrand(@PathVariable(value = "catId") int catId, @PathVariable(value = "brandId") int brandId)
    {
        ApiResponse<List<ProductResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.OK.getCode());
        apiResponse.setMessage(MyApiResponse.OK.getMessage());
        apiResponse.setData(productService.getProductsByCategoryAndBrand(catId, brandId));
        return apiResponse;
    }
}
