package com.bxp.MaysTech_Spring.repository;

import com.bxp.MaysTech_Spring.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage,Integer> {
    List<ProductImage> findByProduct_Id(Integer id);

    ProductImage getFirstByProduct_Id(Integer productId);
}
