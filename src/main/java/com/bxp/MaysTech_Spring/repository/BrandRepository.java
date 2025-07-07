package com.bxp.MaysTech_Spring.repository;

import com.bxp.MaysTech_Spring.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
    boolean existsByName(String name);
}

