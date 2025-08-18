package com.bxp.MaysTech_Spring.controller;

import com.bxp.MaysTech_Spring.dto.ApiResponse;
import com.bxp.MaysTech_Spring.dto.product.ProductRequest;
import com.bxp.MaysTech_Spring.dto.product.ProductResponse;
import com.bxp.MaysTech_Spring.exception.MyApiResponse;
import com.bxp.MaysTech_Spring.service.ProductService;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
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



    @GetMapping("/products/{prodId}")
    public ApiResponse getProductsById(@PathVariable int prodId)
    {
        ApiResponse<ProductResponse> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.OK.getCode());
        apiResponse.setMessage(MyApiResponse.OK.getMessage());
        apiResponse.setData(productService.getProductById(prodId));


        return apiResponse;
    }



    @GetMapping("/products")
    public ApiResponse<List<ProductResponse>> getProductList(@Nullable @RequestParam Integer categoryId, @Nullable @RequestParam Integer brandId)
    {
        ApiResponse<List<ProductResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.OK.getCode());
        apiResponse.setMessage(MyApiResponse.OK.getMessage());

        if(categoryId == null && brandId == null)
        {
            apiResponse.setData(productService.getProducts());
        }
        else if(brandId == null)
        {
            apiResponse.setData(productService.getProductsByCategory(categoryId));
        }
        else
        {
            apiResponse.setData(productService.getProductsByCategoryAndBrand(categoryId, brandId));
        }

        return apiResponse;
    }




    @PatchMapping("/products/{prodId}")
    public ApiResponse<ProductResponse> updateProduct(@PathVariable int prodId, @RequestBody ProductRequest request)
    {
        ApiResponse<ProductResponse> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.OK.getCode());
        apiResponse.setMessage(MyApiResponse.OK.getMessage());
        apiResponse.setData(productService.updateProduct(prodId, request));
        return apiResponse;
    }


    @GetMapping("/products/new")
    public ApiResponse<List<ProductResponse>> getNewProduct()
    {
        ApiResponse<List<ProductResponse>>  apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.OK.getCode());
        apiResponse.setMessage(MyApiResponse.OK.getMessage());
        apiResponse.setData(productService.getNewProduct());
        return apiResponse;
    }

    @GetMapping("/products/search")
    public ApiResponse<List<ProductResponse>> searchProductByName(@RequestParam String kw)
    {
        ApiResponse<List<ProductResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.OK.getCode());
        apiResponse.setMessage(MyApiResponse.OK.getMessage());
        apiResponse.setData(productService.searchProductByName(kw));
        return apiResponse;
    }

    @GetMapping("/products/sale")
    public ApiResponse<List<ProductResponse>> getProductCanAddSale()
    {
        ApiResponse<List<ProductResponse>>  apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.OK.getCode());
        apiResponse.setMessage(MyApiResponse.OK.getMessage());
        apiResponse.setData(productService.getProductCanAddSale());
        return apiResponse;
    }


}
