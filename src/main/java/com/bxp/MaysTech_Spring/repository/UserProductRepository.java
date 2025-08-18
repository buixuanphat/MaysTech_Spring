package com.bxp.MaysTech_Spring.repository;

import com.bxp.MaysTech_Spring.dto.user_product.UserProductResponse;
import com.bxp.MaysTech_Spring.dto.user_product.UserProductTotalResponse;
import com.bxp.MaysTech_Spring.entity.UserProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProductRepository extends JpaRepository<UserProduct, Integer> {
    UserProduct findByProduct_IdAndUser_Id(int productId, int userId);


    @Query(value = """
    SELECT
        SUM(up.amount) AS totalAmount,
        SUM(up.amount * p.price * (1 - COALESCE(s.percent, 0)/100)) AS totalPrice
    FROM product p
    JOIN user_product up ON p.id = up.product_id
    LEFT JOIN detail_sale ds ON p.id = ds.product_id
    LEFT JOIN sale s ON ds.sale_id = s.id
    WHERE up.is_chosen = true and up.user_id = :userId
    """, nativeQuery = true)
    Object getCartTotalByUserId(@Param("userId") int userId);




    List<UserProduct> findAllByUser_Id(Integer userId);

    List<UserProduct> findAllByUser_IdAndIsChosen(Integer userId, Boolean isChosen);
}