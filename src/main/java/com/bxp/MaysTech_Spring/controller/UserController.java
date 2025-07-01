package com.bxp.MaysTech_Spring.controller;

import com.bxp.MaysTech_Spring.dto.response.ApiResponse;
import com.bxp.MaysTech_Spring.dto.request.UserRegisterRequest;
import com.bxp.MaysTech_Spring.dto.request.UserUpdateRequest;
import com.bxp.MaysTech_Spring.entity.User;
import com.bxp.MaysTech_Spring.exception.ResponseCode;
import com.bxp.MaysTech_Spring.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/users")
    ApiResponse<User> register (@RequestBody @Valid UserRegisterRequest request)
    {
        ApiResponse<User> apiResponse = new ApiResponse();
        apiResponse.setStatusCode(ResponseCode.CREATED.getCode());
        apiResponse.setMessage(ResponseCode.CREATED.getMessage());
        apiResponse.setData(userService.register(request));
        return apiResponse;
    }

    @GetMapping("/users")
    ApiResponse<List<User>> getUsers()
    {
        ApiResponse<List<User>> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(ResponseCode.OK.getCode());
        apiResponse.setMessage(ResponseCode.OK.getMessage());
        apiResponse.setData(userService.getUsers());
        return apiResponse;
    }

    @GetMapping("/users/{userId}")
    public ApiResponse<User> getUserById(@PathVariable Long userId)
    {
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(ResponseCode.OK.getCode());
        apiResponse.setMessage(ResponseCode.OK.getMessage());
        apiResponse.setData(userService.getUserById(userId));
        return apiResponse;
    }

    @PutMapping("/users/{userId}")
    public ApiResponse<User> updateUser(@PathVariable long userId, @RequestBody UserUpdateRequest request)
    {
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(ResponseCode.OK.getCode());
        apiResponse.setMessage(ResponseCode.OK.getMessage());
        apiResponse.setData(userService.updateUser(userId, request));
        return apiResponse;
    }

    @DeleteMapping("/users/{userId}")
    public ApiResponse deleteUser(@PathVariable long userId)
    {
        userService.deleteUser(userId);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatusCode(ResponseCode.NO_CONTENT.getCode());
        apiResponse.setMessage(ResponseCode.NO_CONTENT.getMessage());
        return apiResponse;
    }

}
