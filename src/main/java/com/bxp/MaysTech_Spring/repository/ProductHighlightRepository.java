package com.bxp.MaysTech_Spring.repository;

import com.bxp.MaysTech_Spring.entity.ProductHighlight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductHighlightRepository extends JpaRepository<ProductHighlight, Integer> {
}
