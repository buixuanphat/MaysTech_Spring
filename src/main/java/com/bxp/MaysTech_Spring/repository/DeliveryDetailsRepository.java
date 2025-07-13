package com.bxp.MaysTech_Spring.repository;

import com.bxp.MaysTech_Spring.entity.DeliveryDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryDetailsRepository extends JpaRepository<DeliveryDetail, Integer> {
    List<DeliveryDetail> findAllByDelivery_Id(int deliveryId);

}
