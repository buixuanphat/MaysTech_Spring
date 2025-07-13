package com.bxp.MaysTech_Spring.service;

import com.bxp.MaysTech_Spring.dto.user.UserLoginRequest;
import com.bxp.MaysTech_Spring.dto.user.UserRegisterRequest;
import com.bxp.MaysTech_Spring.dto.user.UserResponse;
import com.bxp.MaysTech_Spring.dto.user.UserUpdateRequest;
import com.bxp.MaysTech_Spring.entity.User;
import com.bxp.MaysTech_Spring.exception.AppException;
import com.bxp.MaysTech_Spring.exception.MyApiResponse;
import com.bxp.MaysTech_Spring.repository.UserRepository;
import com.bxp.MaysTech_Spring.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtil jwtUtil;


    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private ResourcePatternResolver resourcePatternResolver;

    public UserResponse register(UserRegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AppException(MyApiResponse.USER_ALREADY_EXISTS);
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User savedUser = userRepository.save(user);
        return toUserResponse(savedUser);
    }

    public User getUserEntityByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new AppException(MyApiResponse.NOT_FOUND);
        }
        return user;
    }


    public String login(UserLoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail());

        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return null;
        }

        return jwtUtil.generateToken(user.getEmail());
    }



    public List<UserResponse> getUsers() {
        List<User> userList = userRepository.findAll();
        return userList.stream().map(this::toUserResponse).toList();
    }


    public UserResponse getUserById(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(MyApiResponse.NOT_FOUND));
        return toUserResponse(user);
    }

    public User getUserEntityById(int userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new AppException(MyApiResponse.NOT_FOUND));
    }

    public UserResponse updateUser(int userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(MyApiResponse.NOT_FOUND));

        user.setProvince(request.getProvince());
        user.setDistrict(request.getDistrict());
        user.setWard(request.getWard());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setAdressDetails(request.getAddressDetails());
        user.setProvinceId(request.getProvinceId());
        user.setDistrictId(request.getDistrictId());
        user.setWardId(request.getWardId());

        userRepository.save(user);

        return toUserResponse(user);
    }

    public void deleteUser(int userId) {
        if (!userRepository.existsById(userId)) {
            throw new AppException(MyApiResponse.NOT_FOUND);
        }
        userRepository.deleteById(userId);
    }

    public UserResponse toUserResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setProvince(user.getProvince());
        response.setDistrict(user.getDistrict());
        response.setWard(user.getWard());
        response.setProvinceId(user.getProvinceId());
        response.setDistrictId(user.getDistrictId());
        response.setWardId(user.getWardId());
        response.setAddressDetails(user.getAdressDetails());
        response.setPhoneNumber(user.getPhoneNumber());
        return response;
    }
}
