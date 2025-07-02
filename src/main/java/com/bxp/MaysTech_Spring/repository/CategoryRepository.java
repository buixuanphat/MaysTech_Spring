package com.bxp.MaysTech_Spring.repository;

import com.bxp.MaysTech_Spring.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    public boolean existsByName(String name);
}
