package com.bxp.MaysTech_Spring.controller;

import com.bxp.MaysTech_Spring.dto.ApiResponse;
import com.bxp.MaysTech_Spring.dto.brand.BrandRequest;
import com.bxp.MaysTech_Spring.entity.Brand;
import com.bxp.MaysTech_Spring.exception.MyApiResponse;
import com.bxp.MaysTech_Spring.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BrandController {
    @Autowired
    BrandService brandService;

    @GetMapping("/brands")
    public ApiResponse<List<Brand>> getBrands()
    {
        ApiResponse<List<Brand>> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.OK.getCode());
        apiResponse.setMessage(MyApiResponse.OK.getMessage());
        apiResponse.setData(brandService.getBrands());
        return apiResponse;
    }

    @GetMapping("/brands/{brandId}")
    public ApiResponse<Brand> getBrand(@PathVariable int brandId)
    {
        ApiResponse<Brand> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.OK.getCode());
        apiResponse.setMessage(MyApiResponse.OK.getMessage());
        apiResponse.setData(brandService.getBrandById(brandId));
        return apiResponse;
    }

    @PostMapping("/brands")
    public ApiResponse<Brand> createBrand(@RequestBody BrandRequest request)
    {
        ApiResponse<Brand> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.CREATED.getCode());
        apiResponse.setMessage(MyApiResponse.CREATED.getMessage());
        apiResponse.setData(brandService.createBrand(request));
        return apiResponse;
    }

    @PutMapping("/brands/{brandId}")
    public ApiResponse<Brand> updateBrand(@PathVariable int brandId, @RequestBody BrandRequest request)
    {
        ApiResponse<Brand> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.OK.getCode());
        apiResponse.setMessage(MyApiResponse.OK.getMessage());
        apiResponse.setData(brandService.updateBrand(brandId, request));
        return apiResponse;
    }

    @DeleteMapping("brands/{brandId}")
    public ApiResponse<Brand> deleteBrand(@PathVariable int brandId)
    {
        brandService.deleteBrand(brandId);
        ApiResponse<Brand> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.NO_CONTENT.getCode());
        apiResponse.setMessage(MyApiResponse.NO_CONTENT.getMessage());
        return apiResponse;
    }

}
