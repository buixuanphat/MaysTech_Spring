package com.bxp.MaysTech_Spring.controller;

import com.bxp.MaysTech_Spring.dto.ApiResponse;
import com.bxp.MaysTech_Spring.dto.category.CategoryRequest;
import com.bxp.MaysTech_Spring.entity.Category;
import com.bxp.MaysTech_Spring.exception.MyApiResponse;
import com.bxp.MaysTech_Spring.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/categories")
    public ApiResponse<List<Category>> getCategories()
    {
        ApiResponse<List<Category>> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.OK.getCode());
        apiResponse.setMessage(MyApiResponse.OK.getMessage());
        apiResponse.setData(categoryService.getCategory());
        return apiResponse;
    }

    @GetMapping("/categories/{catId}")
    public ApiResponse<Category> getCategoryById(@PathVariable int catId)
    {
        ApiResponse<Category> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.OK.getCode());
        apiResponse.setMessage(MyApiResponse.OK.getMessage());
        apiResponse.setData(categoryService.getCategoryById(catId));
        return apiResponse;
    }

    @PostMapping("/categories")
    public ApiResponse<Category> createCategory(@RequestBody CategoryRequest request)
    {
        ApiResponse<Category> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.CREATED.getCode());
        apiResponse.setMessage(MyApiResponse.CREATED.getMessage());
        apiResponse.setData(categoryService.createCategory(request));
        return apiResponse;
    }

    @PatchMapping ("/categories/{catId}")
    public ApiResponse<Category> updateCategory(@PathVariable int catId, @RequestBody CategoryRequest request)
    {
        ApiResponse<Category> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.OK.getCode());
        apiResponse.setMessage(MyApiResponse.OK.getMessage());
        apiResponse.setData(categoryService.updateCategory(catId, request));
        return apiResponse;
    }

    @DeleteMapping("/categories/{catId}")
    public ApiResponse deleteCategory(@PathVariable int catId)
    {
        categoryService.deleteCategory(catId);

        ApiResponse<Category> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.NO_CONTENT.getCode());
        apiResponse.setMessage(MyApiResponse.NO_CONTENT.getMessage());
        return apiResponse;
    }
}
