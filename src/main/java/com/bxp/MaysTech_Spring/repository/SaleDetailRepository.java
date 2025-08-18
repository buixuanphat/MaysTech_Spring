package com.bxp.MaysTech_Spring.repository;

import com.bxp.MaysTech_Spring.entity.DetailSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleDetailRepository extends JpaRepository<DetailSale, Integer> {
    DetailSale getFirstByProduct_Id(Integer productId);

    List<DetailSale> findAllBySale_Id(Integer saleId);
}
