package com.bxp.MaysTech_Spring.service;

import com.bxp.MaysTech_Spring.dto.product_highlight.ProductHighlightResponse;
import com.bxp.MaysTech_Spring.entity.ProductHighlight;
import com.bxp.MaysTech_Spring.repository.ProductHighlightRepository;
import com.bxp.MaysTech_Spring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductHighlightService {

    @Autowired
    private ProductHighlightRepository productHighlightRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<ProductHighlightResponse> getProductHighlight() {
        List<ProductHighlight> productHighlightList = productHighlightRepository.findAll();
        return productHighlightList.stream().map(this::convertEntityToResponse).toList();
    }

    ProductHighlightResponse convertEntityToResponse(ProductHighlight productHighlight) {
        ProductHighlightResponse productHighlightResponse = new ProductHighlightResponse();
        productHighlightResponse.setId(productHighlight.getId());
        productHighlightResponse.setProductId(productHighlight.getProduct().getId());
        productHighlightResponse.setImage(productHighlight.getImage());
        return productHighlightResponse;
    }

}
