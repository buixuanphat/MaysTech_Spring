package com.bxp.MaysTech_Spring.service;

import com.bxp.MaysTech_Spring.dto.product_image.ProductImageDTO;
import com.bxp.MaysTech_Spring.entity.ProductImage;
import com.bxp.MaysTech_Spring.repository.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductImageService {

    @Autowired
    ProductImageRepository productImageRepository;

    public List<ProductImageDTO> getImageByProduct(int productId)
    {
        return productImageRepository.findByProduct_Id(productId).stream().map(i ->
        {
            ProductImageDTO productImageDTO = new ProductImageDTO();
            productImageDTO.setProductId(i.getProduct().getId());
            productImageDTO.setImage(i.getImage());
            return productImageDTO;
        }).collect(Collectors.toList());
    }
}
