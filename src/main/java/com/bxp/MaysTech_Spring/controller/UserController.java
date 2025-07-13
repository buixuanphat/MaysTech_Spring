package com.bxp.MaysTech_Spring.controller;

import com.bxp.MaysTech_Spring.dto.ApiResponse;
import com.bxp.MaysTech_Spring.dto.user.UserLoginRequest;
import com.bxp.MaysTech_Spring.dto.user.UserRegisterRequest;
import com.bxp.MaysTech_Spring.dto.user.UserResponse;
import com.bxp.MaysTech_Spring.dto.user.UserUpdateRequest;
import com.bxp.MaysTech_Spring.entity.User;
import com.bxp.MaysTech_Spring.exception.MyApiResponse;
import com.bxp.MaysTech_Spring.service.UserService;
import com.bxp.MaysTech_Spring.utils.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/auth/register")
    ApiResponse<UserResponse> register (@RequestBody UserRegisterRequest request)
    {
        ApiResponse<UserResponse> apiResponse = new ApiResponse();
        apiResponse.setStatusCode(MyApiResponse.CREATED.getCode());
        apiResponse.setMessage(MyApiResponse.CREATED.getMessage());
        apiResponse.setData(userService.register(request));
        return apiResponse;
    }

    @PostMapping("/auth/login")
    public ApiResponse<String> login(@RequestBody UserLoginRequest request) {
        String token = userService.login(request);

        ApiResponse<String> apiResponse = new ApiResponse<>();
        if (token != null) {
            apiResponse.setStatusCode(200);
            apiResponse.setMessage("Login thành công");
            apiResponse.setData(token);
        } else {
            apiResponse.setStatusCode(400);
            apiResponse.setMessage("Sai tài khoản hoặc mật khẩu");
            apiResponse.setData(null);
        }
        return apiResponse;
    }


    @GetMapping("/users")
    ApiResponse<List<UserResponse>> getUsers()
    {
        ApiResponse<List<UserResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.OK.getCode());
        apiResponse.setMessage(MyApiResponse.OK.getMessage());
        apiResponse.setData(userService.getUsers());
        return apiResponse;
    }

    @GetMapping("/auth/me")
    public ApiResponse<UserResponse> getCurrentUser(@RequestHeader("Authorization") String authHeader) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();

        try {
            // Cắt chuỗi "Bearer <token>"
            String token = authHeader.replace("Bearer ", "");

            // Lấy email từ token
            String email = jwtUtil.extractEmail(token);

            // Tìm user theo email
            User user = userService.getUserEntityByEmail(email); // viết thêm hàm này trong UserService

            apiResponse.setStatusCode(200);
            apiResponse.setMessage("Lấy thông tin thành công");
            apiResponse.setData(userService.toUserResponse(user)); // trả về DTO
            return apiResponse;

        } catch (Exception e) {
            apiResponse.setStatusCode(401);
            apiResponse.setMessage("Token không hợp lệ hoặc đã hết hạn");
            apiResponse.setData(null);
            return apiResponse;
        }
    }


    @GetMapping("/users/{userId}")
    public ApiResponse<UserResponse> getUserById(@PathVariable int userId)
    {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.OK.getCode());
        apiResponse.setMessage(MyApiResponse.OK.getMessage());
        apiResponse.setData(userService.getUserById(userId));
        return apiResponse;
    }

    @DeleteMapping("/users/{userId}")
    public ApiResponse deleteUser(@PathVariable int userId)
    {
        userService.deleteUser(userId);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatusCode(MyApiResponse.NO_CONTENT.getCode());
        apiResponse.setMessage(MyApiResponse.NO_CONTENT.getMessage());
        return apiResponse;
    }

    @PatchMapping("/users/{userId}")
    public ApiResponse<UserResponse> updateUser(@PathVariable("userId") int userId, @RequestBody UserUpdateRequest request)
    {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(MyApiResponse.OK.getCode());
        apiResponse.setMessage(MyApiResponse.OK.getMessage());
        apiResponse.setData(userService.updateUser(userId, request));
        return apiResponse;
    }

}
