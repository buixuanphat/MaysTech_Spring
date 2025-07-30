package com.bxp.MaysTech_Spring.repository;

import com.bxp.MaysTech_Spring.dto.user_product.ItemProductDTO;
import com.bxp.MaysTech_Spring.dto.user_product.UserProductTotalResponse;
import com.bxp.MaysTech_Spring.entity.UserProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProductRepository extends JpaRepository<UserProduct, Integer> {
    UserProduct findByProduct_IdAndUser_Id(int productId, int usertId);

    @Query("SELECT new com.bxp.MaysTech_Spring.dto.user_product.ItemProductDTO(" +
            "up.id, p.id, p.name, p.price, up.amount, p.image, p.price * up.amount, up.isChosen) " +
            "FROM UserProduct up " +
            "JOIN up.product p " +
            "WHERE up.user.id = :userId " +
            "ORDER BY up.isChosen DESC")

    List<ItemProductDTO> findCartItemsByUserId(@Param("userId") int userId);


    @Query("SELECT new com.bxp.MaysTech_Spring.dto.user_product.UserProductTotalResponse(" +
            "CAST(COUNT(up.id) AS int), " +
            "COALESCE(SUM(up.amount * p.price), 0), " +
            "COALESCE(SUM(p.weight * up.amount), 0)) " +
            "FROM UserProduct up JOIN up.product p " +
            "WHERE up.isChosen = true AND up.user.id = :userId")
    UserProductTotalResponse getCartTotalByUserId(@Param("userId") int userId);


}
