package com.bxp.MaysTech_Spring.service;

import com.bxp.MaysTech_Spring.dto.user.UserRegisterRequest;
import com.bxp.MaysTech_Spring.dto.user.UserUpdateRequest;
import com.bxp.MaysTech_Spring.entity.User;
import com.bxp.MaysTech_Spring.exception.AppException;
import com.bxp.MaysTech_Spring.exception.MyApiResponse;
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
            throw new AppException(MyApiResponse.USER_ALREADY_EXISTS);
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

    public User getUserById(int userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new AppException(MyApiResponse.NOT_FOUND));
    }

    public User updateUser(int userId, UserUpdateRequest request) {
        User user = this.getUserById(userId);

        user.setCity(request.getCity());
        user.setDistrict(request.getDistrict());
        user.setWard(request.getWard());
        user.setStreet(request.getStreet());
        user.setHouseNumber(request.getHouseNumber());

        return userRepository.save(user);
    }

    public void deleteUser(int userId) {
        if (!userRepository.existsById(userId)) {
            throw new AppException(MyApiResponse.NOT_FOUND);
        }
        userRepository.deleteById(userId);
    }
}
