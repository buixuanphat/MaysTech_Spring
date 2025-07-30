package com.bxp.MaysTech_Spring.repository;

import com.bxp.MaysTech_Spring.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Integer> {


    List<Rating> findAllByProduct_Id(Integer productId);
}
