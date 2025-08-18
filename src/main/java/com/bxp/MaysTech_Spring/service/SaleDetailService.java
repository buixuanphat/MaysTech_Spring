package com.bxp.MaysTech_Spring.service;

import com.bxp.MaysTech_Spring.dto.product.ProductResponse;
import com.bxp.MaysTech_Spring.dto.sale_detail.SaleDetailRequest;
import com.bxp.MaysTech_Spring.dto.sale_detail.SaleDetailResponse;
import com.bxp.MaysTech_Spring.entity.DetailSale;
import com.bxp.MaysTech_Spring.entity.Product;
import com.bxp.MaysTech_Spring.repository.ProductRepository;
import com.bxp.MaysTech_Spring.repository.SaleDetailRepository;
import com.bxp.MaysTech_Spring.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleDetailService {
    @Autowired
    SaleDetailRepository saleDetailRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SaleRepository saleRepository;

    @Autowired
    ProductService productService;

    public SaleDetailResponse createSaleDetail(SaleDetailRequest saleDetailRequest){
        DetailSale  detailSale = new DetailSale();
        detailSale.setSale(saleRepository.getById(saleDetailRequest.getSaleId()));
        detailSale.setProduct(productRepository.getById(saleDetailRequest.getProductId()));
        saleDetailRepository.save(detailSale);
        ProductResponse product = productService.getProductById(detailSale.getProduct().getId());
        return convertEntityToResponse(detailSale, product);
    }

    public SaleDetailResponse deleteSaleDetail(int id){
        DetailSale detailSale = saleDetailRepository.getById(id);
        saleDetailRepository.delete(detailSale);
        ProductResponse product = productService.getProductById(detailSale.getProduct().getId());
        return convertEntityToResponse(detailSale, product);
    }

    public List<SaleDetailResponse> getAllDetailOfSale(int id){
        return saleDetailRepository.findAllBySale_Id(id).stream().map(detailSale ->
        {
            ProductResponse product = productService.getProductById(detailSale.getProduct().getId());
            return convertEntityToResponse(detailSale, product);
        }).toList();

    }

    SaleDetailResponse convertEntityToResponse(DetailSale detailSale, ProductResponse productResponse){
        SaleDetailResponse saleDetailResponse = new SaleDetailResponse();
        saleDetailResponse.setId(detailSale.getId());
        saleDetailResponse.setProductId(detailSale.getProduct().getId());
        saleDetailResponse.setSaleId(detailSale.getSale().getId());
        saleDetailResponse.setName(productResponse.getName());
        saleDetailResponse.setPrice(productResponse.getPrice());
        saleDetailResponse.setImageUrl(productResponse.getImageUrl());
        return saleDetailResponse;
    }
}
