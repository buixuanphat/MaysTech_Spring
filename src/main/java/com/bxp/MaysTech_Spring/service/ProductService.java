package com.bxp.MaysTech_Spring.service;

import com.bxp.MaysTech_Spring.dto.product.ProductRequest;
import com.bxp.MaysTech_Spring.dto.product.ProductResponse;
import com.bxp.MaysTech_Spring.entity.*;
import com.bxp.MaysTech_Spring.exception.AppException;
import com.bxp.MaysTech_Spring.exception.MyApiResponse;
import com.bxp.MaysTech_Spring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductImageRepository productImageRepository;

    @Autowired
    SaleDetailRepository saleDetailRepository;

    @Autowired
    SaleRepository saleRepository;

    @Autowired
    UserProductRepository userProductRepository;

    public ProductResponse createProduct(ProductRequest request) {
        if (productRepository.existsByName(request.getName())) {
            throw new AppException(MyApiResponse.PRODUCT_ALREADY_EXISTS);
        }
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        product.setStock(request.getStock());
        product.setActive(true);
        product.setBrand(brandRepository.getById(request.getBrandId()));
        product.setCategory(categoryRepository.getById(request.getCategoryId()));
        ProductImage image = productImageRepository.getFirstByProduct_Id(product.getId());
        DetailSale detailSale = saleDetailRepository.getFirstByProduct_Id(product.getId());
        Sale sale = null;
        if(detailSale != null) {
            sale = saleRepository.getById(detailSale.getId());
        }
        return convertEntityToResponse(productRepository.save(product), image, sale);
    }



    public ProductResponse getProductById(int id) {
        Product product = productRepository.getById(id);
        ProductImage image = productImageRepository.getFirstByProduct_Id(id);
        DetailSale detailSale = saleDetailRepository.getFirstByProduct_Id(id);
        Sale sale = null;
        if(detailSale != null) {
            sale = saleRepository.getById(detailSale.getSale().getId());
        }
        return convertEntityToResponse(product, image, sale);
    }

//    public ProductResponse updateStock(int id)
//    {
//        List<UserProduct> selectedUserProducts = userProductRepository.findAllByUser_IdAndIsChosen(id, true);
//        for (UserProduct userProduct : selectedUserProducts) {
//            userProductRepository.deleteById(userProduct.getId());
//
//            Product product = productRepository.getById(id);
//            product.setStock(product.getStock() - userProduct.getAmount());
//            productRepository.save(product);
//            ProductImage image = productImageRepository.getFirstByProduct_Id(product.getId());
//            DetailSale detailSale = saleDetailRepository.getFirstByProduct_Id(product.getId());
//            Sale sale = null;
//            if(detailSale != null) {
//                sale = saleRepository.getById(detailSale.getSale().getId());
//            }
//            return convertEntityToResponse(product, image, sale);
//        }
//    }



    public List<ProductResponse> getProducts() {
        List<Product> productList = productRepository.findAll();
        return productList.stream().map(product ->
        {
            ProductImage image = productImageRepository.getFirstByProduct_Id(product.getId());
            DetailSale detailSale = saleDetailRepository.getFirstByProduct_Id(product.getId());
            Sale sale = null;
            if(detailSale != null) {
                sale = saleRepository.getById(detailSale.getSale().getId());
            }
            return convertEntityToResponse(product, image, sale);
        }).toList();
    }



    public List<ProductResponse> getProductsByCategory(int categoryId) {
        List<Product> productList = new ArrayList<>(productRepository.findByCategory_Id(categoryId));
        return productList.stream().map(product ->
        {
            ProductImage image = productImageRepository.getFirstByProduct_Id(product.getId());
            DetailSale detailSale = saleDetailRepository.getFirstByProduct_Id(product.getId());
            Sale sale = null;
            if(detailSale != null) {
                sale = saleRepository.getById(detailSale.getSale().getId());
            }
            return convertEntityToResponse(product, image, sale);
        }).toList();
    }


    public List<ProductResponse> getProductsByCategoryAndBrand(int categoryId, int brandId) {

        List<Product> productList = new ArrayList<>(productRepository.findByCategory_IdAndBrand_Id(categoryId, brandId));
        return productList.stream().map(product ->
        {
            ProductImage image = productImageRepository.getFirstByProduct_Id(product.getId());
            DetailSale detailSale = saleDetailRepository.getFirstByProduct_Id(product.getId());
            Sale sale = null;
            if(detailSale != null) {
                sale = saleRepository.getById(detailSale.getSale().getId());
            }
            return convertEntityToResponse(product, image, sale);
        }).toList();


    }

    public ProductResponse updateProduct(int id, ProductRequest request) {
        Product product = productRepository.getById(id);
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        product.setStock(request.getStock());
        product.setActive(request.getActive());
        product.setBrand(brandRepository.getById(request.getBrandId()));
        product.setCategory(categoryRepository.getById(request.getCategoryId()));

        ProductImage image = productImageRepository.getFirstByProduct_Id(product.getId());
        DetailSale detailSale = saleDetailRepository.getFirstByProduct_Id(product.getId());
        Sale sale = null;
        if(detailSale != null) {
            sale = saleRepository.getById(detailSale.getSale().getId());
        }
        return convertEntityToResponse(productRepository.save(product), image, sale);
    }




    public List<ProductResponse> getNewProduct()
    {
        return productRepository.getNewProduct().stream().map(p ->
        {
            ProductImage image = productImageRepository.getFirstByProduct_Id(p.getId());
            DetailSale detailSale = saleDetailRepository.getFirstByProduct_Id(p.getId());
            Sale sale = null;
            if(detailSale != null) {
                sale = saleRepository.getById(detailSale.getSale().getId());
            }
            return convertEntityToResponse(p,image, sale);
        }).toList();
    }


    public List<ProductResponse> searchProductByName(String kw)
    {
        return productRepository.findAllByNameContains(kw).stream().map(p ->
        {
            ProductImage image = productImageRepository.getFirstByProduct_Id(p.getId());
            DetailSale detailSale = saleDetailRepository.getFirstByProduct_Id(p.getId());
            Sale sale = null;
            if(detailSale != null) {
                sale = saleRepository.getById(detailSale.getSale().getId());
            }
            return convertEntityToResponse(p, image, sale);
        }).toList();
    }


    public List<ProductResponse> getProductCanAddSale()
    {
        return productRepository.findProductsNotInDetailSale().stream().map(p ->
        {
            ProductImage image = productImageRepository.getFirstByProduct_Id(p.getId());
            DetailSale detailSale = saleDetailRepository.getFirstByProduct_Id(p.getId());
            Sale sale = null;
            if(detailSale != null) {
                sale = saleRepository.getById(detailSale.getSale().getId());
            }
            return convertEntityToResponse(p, image, sale);
        }).toList();

    }



    ProductResponse convertEntityToResponse(Product product, ProductImage image , Sale sale) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());
        if(image!=null) response.setImageUrl(image.getImage());
        else response.setImageUrl(null);
        response.setDescription(product.getDescription());
        response.setStock(product.getStock());
        response.setActive(product.getActive());
        response.setCategoryId(product.getCategory().getId());
        response.setBrandId(product.getBrand().getId());
        if (sale != null) {
            response.setSalePrice(product.getPrice()*(1-(sale.getPercent())/100));
        }
        else
            response.setSalePrice(-1.0);
        return response;
    }
}


