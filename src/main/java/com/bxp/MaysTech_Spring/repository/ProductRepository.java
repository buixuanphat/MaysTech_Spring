package com.bxp.MaysTech_Spring.repository;

import com.bxp.MaysTech_Spring.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    boolean existsByName(String name);
    List<Product> findByCategory_Id(int categoryId);
    List<Product> findByCategory_IdAndBrand_Id(int categoryId, int brandId);

}
