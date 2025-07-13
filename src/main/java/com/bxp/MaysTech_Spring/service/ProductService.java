package com.bxp.MaysTech_Spring.service;

import com.bxp.MaysTech_Spring.dto.product.ProductRequest;
import com.bxp.MaysTech_Spring.dto.product.ProductResponse;
import com.bxp.MaysTech_Spring.entity.Product;
import com.bxp.MaysTech_Spring.exception.AppException;
import com.bxp.MaysTech_Spring.exception.MyApiResponse;
import com.bxp.MaysTech_Spring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    BrandService brandService;

    @Autowired
    CategoryService categoryService;

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
        product.setWeight(request.getWeight());
        product.setBrand(brandService.getBrandById(request.getBrand()));
        product.setCategory(categoryService.getCategoryById(request.getCategory()));
        productRepository.save(product);

        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setPrice(product.getPrice());
        productResponse.setDescription(product.getDescription());
        productResponse.setImage(product.getImage());
        productResponse.setStock(product.getStock());
        productResponse.setSalePrice(product.getSalePrice());
        productResponse.setSale(product.getIsSale());
        productResponse.setActive(product.getActive());
        productResponse.setWeight(product.getWeight());
        productResponse.setCategoryId(product.getCategory().getId());
        productResponse.setBrandId(product.getBrand().getId());
        return productResponse;
    }

    public List<ProductResponse> getProducts() {
        return productRepository.findAll().stream().map(product -> {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(product.getId());
            productResponse.setName(product.getName());
            productResponse.setPrice(product.getPrice());
            productResponse.setDescription(product.getDescription());
            productResponse.setImage(product.getImage());
            productResponse.setStock(product.getStock());
            productResponse.setSalePrice(product.getSalePrice());
            productResponse.setSale(product.getIsSale());
            productResponse.setActive(product.getActive());
            productResponse.setWeight(product.getWeight());
            productResponse.setCategoryId(product.getCategory().getId());
            productResponse.setBrandId(product.getBrand().getId());
            return productResponse;
        }).collect(Collectors.toList());
    }

    public ProductResponse getProductById(int id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new AppException(MyApiResponse.NOT_FOUND));
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setPrice(product.getPrice());
        productResponse.setDescription(product.getDescription());
        productResponse.setImage(product.getImage());
        productResponse.setStock(product.getStock());
        productResponse.setSalePrice(product.getSalePrice());
        productResponse.setSale(product.getIsSale());
        productResponse.setActive(product.getActive());
        productResponse.setWeight(product.getWeight());
        productResponse.setCategoryId(product.getCategory().getId());
        productResponse.setBrandId(product.getBrand().getId());
        return productResponse;
    }

    public Product getProductEntityById(int id) {
        return productRepository.findById(id).orElseThrow(() -> new AppException(MyApiResponse.NOT_FOUND));
    }

    public ProductResponse updateProduct(int id, ProductRequest request) {
        if (!productRepository.existsById(id)) {
            throw new AppException(MyApiResponse.NOT_FOUND);
        }
        Product product = productRepository.getReferenceById(id);
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        product.setImage(request.getImage());
        product.setStock(request.getStock());
        product.setWeight(request.getWeight());
        product.setBrand(brandService.getBrandById(request.getBrand()));
        product.setCategory(categoryService.getCategoryById(request.getCategory()));
        productRepository.save(product);

        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setPrice(product.getPrice());
        productResponse.setDescription(product.getDescription());
        productResponse.setImage(product.getImage());
        productResponse.setStock(product.getStock());
        productResponse.setSalePrice(product.getSalePrice());
        productResponse.setSale(product.getIsSale());
        productResponse.setActive(product.getActive());
        productResponse.setWeight(product.getWeight());
        productResponse.setCategoryId(product.getCategory().getId());
        productResponse.setBrandId(product.getBrand().getId());
        return productResponse;
    }

    public void deleteProduct(int id) {
        if (!productRepository.existsById(id)) {
            throw new AppException(MyApiResponse.NOT_FOUND);
        }

        productRepository.deleteById(id);
    }

    public List<ProductResponse> getProductsByCategory(int categoryId) {
        return productRepository.findByCategory_Id(categoryId).stream().map(product ->
        {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(product.getId());
            productResponse.setName(product.getName());
            productResponse.setPrice(product.getPrice());
            productResponse.setDescription(product.getDescription());
            productResponse.setImage(product.getImage());
            productResponse.setStock(product.getStock());
            productResponse.setSalePrice(product.getSalePrice());
            productResponse.setSale(product.getIsSale());
            productResponse.setActive(product.getActive());
            productResponse.setWeight(product.getWeight());
            productResponse.setCategoryId(product.getCategory().getId());
            productResponse.setBrandId(product.getBrand().getId());
            return productResponse;
        }).collect(Collectors.toList());
    }

    public List<ProductResponse> getProductsByCategoryAndBrand(int categoryId, int brandId) {
        return productRepository.findByCategory_IdAndBrand_Id(categoryId, brandId).stream().map(product ->
        {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(product.getId());
            productResponse.setName(product.getName());
            productResponse.setPrice(product.getPrice());
            productResponse.setDescription(product.getDescription());
            productResponse.setImage(product.getImage());
            productResponse.setStock(product.getStock());
            productResponse.setSalePrice(product.getSalePrice());
            productResponse.setSale(product.getIsSale());
            productResponse.setActive(product.getActive());
            productResponse.setWeight(product.getWeight());
            productResponse.setCategoryId(product.getCategory().getId());
            productResponse.setBrandId(product.getBrand().getId());
            return productResponse;
        }).collect(Collectors.toList());
    }
}


