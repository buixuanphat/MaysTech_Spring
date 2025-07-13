package com.bxp.MaysTech_Spring.service;

import com.bxp.MaysTech_Spring.dto.user_product.ItemProductDTO;
import com.bxp.MaysTech_Spring.dto.user_product.UserProductCreateRequest;
import com.bxp.MaysTech_Spring.dto.user_product.UserProductResponse;
import com.bxp.MaysTech_Spring.dto.user_product.UserProductTotalResponse;
import com.bxp.MaysTech_Spring.entity.Product;
import com.bxp.MaysTech_Spring.entity.User;
import com.bxp.MaysTech_Spring.entity.UserProduct;
import com.bxp.MaysTech_Spring.exception.AppException;
import com.bxp.MaysTech_Spring.exception.MyApiResponse;
import com.bxp.MaysTech_Spring.repository.UserProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProductService {
    @Autowired
    UserProductRepository  userProductRepository;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    public List<ItemProductDTO> findCartItemsByUserId(int userId)
    {
        return userProductRepository.findCartItemsByUserId(userId);
    }

    public UserProductResponse addProductToCart(UserProductCreateRequest request) {
        UserProduct userProduct = userProductRepository.findByProduct_IdAndUser_Id(request.getProductId(), request.getUsertId());

        if (userProduct != null) {
            userProduct.setAmount(userProduct.getAmount() + 1);
            userProductRepository.save(userProduct);
        } else {
            userProduct = new UserProduct();

            User user = userService.getUserEntityById(request.getUsertId());

            Product product = productService.getProductEntityById(request.getProductId());
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

    public UserProductTotalResponse getCartTotalByUserId(int id)
    {
        return userProductRepository.getCartTotalByUserId(id);
    }

}
