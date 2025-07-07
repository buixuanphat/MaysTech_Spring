package com.bxp.MaysTech_Spring.repository;

import com.bxp.MaysTech_Spring.dto.ApiResponse;
import com.bxp.MaysTech_Spring.dto.brand.BrandRequest;
import com.bxp.MaysTech_Spring.dto.brand.BrandResponse;
import com.bxp.MaysTech_Spring.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    boolean existsByName(String name);
    List<Product> findByCategory_Id(int categoryId);
    List<Product> findByCategory_IdAndBrand_Id(int categoryId,int brandId);

    @Query("SELECT DISTINCT new com.bxp.MaysTech_Spring.dto.brand.BrandResponse(p.brand.id, p.brand.name, p.brand.logo) " +
            "FROM Product p WHERE p.category.id = :categoryId")
    List<BrandResponse> findDistinctBrandsByCategory(@Param("categoryId") int categoryId);



}
