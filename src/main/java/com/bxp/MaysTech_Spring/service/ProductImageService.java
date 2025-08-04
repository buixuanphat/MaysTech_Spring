package com.bxp.MaysTech_Spring.service;

import com.bxp.MaysTech_Spring.dto.product_image.ProductImageResponse;
import com.bxp.MaysTech_Spring.entity.ProductImage;
import com.bxp.MaysTech_Spring.repository.ProductImageRepository;
import com.bxp.MaysTech_Spring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductImageService {

    @Autowired
    ProductImageRepository productImageRepository;

    @Autowired
    ProductRepository productRepository;

    public List<ProductImageResponse> getImageByProduct(int productId)
    {
        List<ProductImage> productImageList = productImageRepository.findByProduct_Id(productId);
        return productImageList.stream().map(this::convertEntityToResponse).collect(Collectors.toList());

    }

    public void deleteImageOfProduct(int id)
    {
        productImageRepository.deleteById(id);
    }

    public ProductImageResponse updateImageOfProduct(int id, String image)
    {
        ProductImage productImage = productImageRepository.getById(id);
        productImage.setImage(image);
        productImageRepository.save(productImage);
        return convertEntityToResponse(productImage);
    }

    public ProductImageResponse addImageOfProduct(Map<String, String> body)
    {
        ProductImage productImage = new ProductImage();
        productImage.setProduct(productRepository.getById(Integer.parseInt(body.get("productId"))));
        productImage.setImage(body.get("image"));
        productImageRepository.save(productImage);
        return convertEntityToResponse(productImage);
    }

    ProductImageResponse convertEntityToResponse(ProductImage productImage)
    {
        ProductImageResponse productImageResponse = new ProductImageResponse();
        productImageResponse.setId(productImage.getId());
        productImageResponse.setProductId(productImage.getProduct().getId());
        productImageResponse.setImage(productImage.getImage());
        return productImageResponse;
    }


}
