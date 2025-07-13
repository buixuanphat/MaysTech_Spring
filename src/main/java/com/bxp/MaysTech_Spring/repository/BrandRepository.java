package com.bxp.MaysTech_Spring.repository;

import com.bxp.MaysTech_Spring.dto.brand.BrandResponse;
import com.bxp.MaysTech_Spring.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
    boolean existsByName(String name);

    @Query("SELECT DISTINCT b FROM Brand b " +
            "JOIN Product p ON p.brand.id = b.id " +
            "WHERE p.category.id = :categoryId")
    List<Brand> findBrandsOfCategory(@Param("categoryId") int catId);

}

