package com.bxp.MaysTech_Spring.repository;

import com.bxp.MaysTech_Spring.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {
}
