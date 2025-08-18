package com.bxp.MaysTech_Spring.service;

import com.bxp.MaysTech_Spring.dto.product.ProductResponse;
import com.bxp.MaysTech_Spring.dto.user_product.UserProductCreateRequest;
import com.bxp.MaysTech_Spring.dto.user_product.UserProductResponse;
import com.bxp.MaysTech_Spring.dto.user_product.UserProductTotalResponse;
import com.bxp.MaysTech_Spring.entity.Product;
import com.bxp.MaysTech_Spring.entity.User;
import com.bxp.MaysTech_Spring.entity.UserProduct;
import com.bxp.MaysTech_Spring.exception.AppException;
import com.bxp.MaysTech_Spring.exception.MyApiResponse;
import com.bxp.MaysTech_Spring.repository.ProductRepository;
import com.bxp.MaysTech_Spring.repository.UserProductRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Service
public class UserProductService {
    @Autowired
    UserProductRepository  userProductRepository;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    public List<UserProductResponse> getProductInCart(int userId)
    {
        return userProductRepository.findAllByUser_Id(userId).stream().map(this::convertEntityToResponse).toList();
    }

    public UserProductResponse addProductToCart(UserProductCreateRequest request) {
        UserProduct userProduct = userProductRepository.findByProduct_IdAndUser_Id(request.getProductId(), request.getUsertId());

        if (userProduct != null) {
            userProduct.setAmount(userProduct.getAmount() + 1);
            userProductRepository.save(userProduct);
        } else {
            userProduct = new UserProduct();

            User user = userService.getUserEntityById(request.getUsertId());

            Product product = productRepository.getById(request.getProductId());
            userProduct.setUser(user);
            userProduct.setProduct(product);
            userProduct.setAmount(1);
            userProductRepository.save(userProduct);
        }

        UserProductResponse response = new UserProductResponse();
        response.setId(userProduct.getId());
        response.setUserId(userProduct.getUser().getId());
        response.setProductId(userProduct.getProduct().getId());
        response.setAmount(userProduct.getAmount());

        return response;
    }

    public void deleteProductFromCart(int id) {
        UserProduct userProduct = userProductRepository.findById(id)
                .orElseThrow(() -> new AppException(MyApiResponse.NOT_FOUND));

        int newAmount = userProduct.getAmount() - 1;

        if (newAmount <= 0) {
            userProductRepository.deleteById(id);
        } else {
            userProduct.setAmount(newAmount);
            userProductRepository.save(userProduct);
        }
    }

    public void deleteSelected(int id)
    {
        List<UserProduct> selectedUserProducts = userProductRepository.findAllByUser_IdAndIsChosen(id, true);
        for (UserProduct userProduct : selectedUserProducts) {
            userProductRepository.deleteById(userProduct.getId());
        }
    }


    public UserProductResponse getById(int id)
    {
        UserProduct userProduct = userProductRepository.findById(id).orElseThrow(() -> new AppException(MyApiResponse.NOT_FOUND));
        UserProductResponse response = new UserProductResponse();
        response.setId(userProduct.getId());
        response.setUserId(userProduct.getUser().getId());
        response.setProductId(userProduct.getProduct().getId());
        response.setAmount(userProduct.getAmount());
        return response;
    }

    public UserProductResponse chosen(int id, boolean isChosen)
    {
        UserProduct userProduct = userProductRepository.findById(id).orElseThrow(()->new AppException(MyApiResponse.NOT_FOUND));
        userProduct.setIsChosen(isChosen);
        userProductRepository.save(userProduct);

        UserProductResponse response = new UserProductResponse();
        response.setId(userProduct.getId());
        response.setUserId(userProduct.getUser().getId());
        response.setProductId(userProduct.getProduct().getId());
        response.setAmount(userProduct.getAmount());
        response.setChosen(isChosen);
        return response;
    }

    public UserProductTotalResponse getCartTotalByUserId(int id) {
        Object result = userProductRepository.getCartTotalByUserId(id);

        UserProductTotalResponse response = new UserProductTotalResponse();

        if (result != null && result.getClass().isArray()) {
            Object[] row = (Object[]) result;

            response.setTotalAmount(row[0] instanceof Number ? ((Number) row[0]).intValue() : 0);

            response.setTotalPrice(row[1] instanceof Number ? ((Number) row[1]).doubleValue() : 0.0);
        }

        return response;
    }









    UserProductResponse convertEntityToResponse(UserProduct userProduct)
    {
        UserProductResponse response = new UserProductResponse();
        response.setId(userProduct.getId());
        response.setUserId(userProduct.getUser().getId());
        response.setProductId(userProduct.getProduct().getId());
        response.setAmount(userProduct.getAmount());
        response.setChosen(userProduct.getIsChosen());
        ProductResponse product = productService.getProductById(userProduct.getProduct().getId());
        response.setProductImage(product.getImageUrl());
        response.setProductName(product.getName());
        if(product.getSalePrice()!=-1)
        {
            response.setProductPrice(product.getSalePrice());
        }
        else
        {
            response.setProductPrice(product.getPrice());
        }

        response.setTotalPrice(product.getPrice()*userProduct.getAmount());

        return response;
    }

}
