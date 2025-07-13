package com.bxp.MaysTech_Spring.repository;

import com.bxp.MaysTech_Spring.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery,Integer> {
    List<Delivery> findAllByUser_Id(Integer userId);
}
