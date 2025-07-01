package com.bxp.MaysTech_Spring.service;

import com.bxp.MaysTech_Spring.dto.request.UserRegisterRequest;
import com.bxp.MaysTech_Spring.dto.request.UserUpdateRequest;
import com.bxp.MaysTech_Spring.entity.User;
import com.bxp.MaysTech_Spring.exception.AppException;
import com.bxp.MaysTech_Spring.exception.ResponseCode;
import com.bxp.MaysTech_Spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User register(UserRegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AppException(ResponseCode.USER_ALREADY_EXISTS);
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ResponseCode.NOT_FOUND));
    }

    public User updateUser(long userId, UserUpdateRequest request) {
        User user = this.getUserById(userId);

        user.setCity(request.getCity());
        user.setDistrict(request.getDistrict());
        user.setWard(request.getWard());
        user.setStreet(request.getStreet());
        user.setHouseNumber(request.getHouseNumber());

        return userRepository.save(user);
    }

    public void deleteUser(long userId) {
        if (!userRepository.existsById(userId)) {
            throw new AppException(ResponseCode.NOT_FOUND);
        }
        userRepository.deleteById(userId);
    }
}
