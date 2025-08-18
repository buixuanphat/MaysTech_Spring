package com.bxp.MaysTech_Spring.repository;

import com.bxp.MaysTech_Spring.dto.delivery.BestSellingResponse;
import com.bxp.MaysTech_Spring.dto.product.ProductResponse;
import com.bxp.MaysTech_Spring.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByCategory_Id(Integer categoryId);

    List<Product> findByCategory_IdAndBrand_Id(Integer categoryId, Integer brandId);

    boolean existsByName(String name);


    @Query(value = """
            SELECT * FROM maystech.product
            order by id desc
            limit 10
            """, nativeQuery = true)
    List<Product> getNewProduct();

    List<Product> findAllByNameContains(String name);

    @Query(value = "SELECT * FROM product WHERE id NOT IN (" +
            "SELECT p.id FROM detail_sale ds " +
            "JOIN product p ON ds.product_id = p.id " +
            "GROUP BY p.id)",
            nativeQuery = true)
    List<Product> findProductsNotInDetailSale();
}
