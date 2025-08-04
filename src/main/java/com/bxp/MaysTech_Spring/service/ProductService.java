package com.bxp.MaysTech_Spring.service;

import com.bxp.MaysTech_Spring.dto.product.ProductRequest;
import com.bxp.MaysTech_Spring.dto.product.ProductResponse;
import com.bxp.MaysTech_Spring.entity.Product;
import com.bxp.MaysTech_Spring.exception.AppException;
import com.bxp.MaysTech_Spring.exception.MyApiResponse;
import com.bxp.MaysTech_Spring.repository.BrandRepository;
import com.bxp.MaysTech_Spring.repository.CategoryRepository;
import com.bxp.MaysTech_Spring.repository.ProductRepository;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    CategoryRepository categoryRepository;
    private ResourcePatternResolver resourcePatternResolver;

    public ProductResponse createProduct(ProductRequest request) {
        if (productRepository.existsByName(request.getName())) {
            throw new AppException(MyApiResponse.PRODUCT_ALREADY_EXISTS);
        }
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        product.setImage(request.getImage());
        product.setStock(request.getStock());
        product.setBrand(brandRepository.getById(request.getBrandId()));
        product.setCategory(categoryRepository.getById(request.getCategoryId()));
        return convertEntityToResponse(productRepository.save(product));
    }

    public ProductResponse getProductById(int id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new AppException(MyApiResponse.NOT_FOUND));
        return convertEntityToResponse(product);
    }

    public List<ProductResponse> getProducts() {
        return productRepository.findAll().stream().map(this::convertEntityToResponse).collect(Collectors.toList());
    }



    public List<ProductResponse> getProductsByCategory(int categoryId, @Nullable String ordered) {
        List<Product> productList = new ArrayList<>(productRepository.findByCategory_Id(categoryId));

        if (ordered != null) {
            if (ordered.equals("asc")) {
                productList.sort(Comparator.comparing(Product::getPrice));
            } else if (ordered.equals("desc")) {
                productList.sort(Comparator.comparing(Product::getPrice).reversed());
            }
        }

        return productList.stream()
                .map(this::convertEntityToResponse)
                .collect(Collectors.toList());
    }


    public List<ProductResponse> getProductsByCategoryAndBrand(int categoryId, int brandId, @Nullable String ordered) {

        List<Product> productList = new ArrayList<>(productRepository.findByCategory_IdAndBrand_Id(categoryId, brandId));

        if (ordered != null) {
            if (ordered.equals("asc")) {
                productList.sort(Comparator.comparing(Product::getPrice));
            } else if (ordered.equals("desc")) {
                productList.sort(Comparator.comparing(Product::getPrice).reversed());
            }
        }

        return productList.stream().map(this::convertEntityToResponse).collect(Collectors.toList());


    }

    public ProductResponse updateProduct(int id, ProductRequest request) {
        Product product = productRepository.getById(id);
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        product.setImage(request.getImage());
        product.setStock(request.getStock());
        product.setBrand(brandRepository.getById(request.getBrandId()));
        product.setCategory(categoryRepository.getById(request.getCategoryId()));
        return convertEntityToResponse(productRepository.save(product));
    }

    ProductResponse convertEntityToResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setPrice(product.getPrice());
        productResponse.setImage(product.getImage());
        productResponse.setDescription(product.getDescription());
        productResponse.setStock(product.getStock());
        if(product.getSalePrice() != null) {
            productResponse.setSalePrice(product.getSalePrice());
        }
        productResponse.setSale(product.getIsSale());
        productResponse.setActive(product.getActive());
        productResponse.setCategoryId(product.getCategory().getId());
        productResponse.setBrandId(product.getBrand().getId());
        return productResponse;
    }
}


