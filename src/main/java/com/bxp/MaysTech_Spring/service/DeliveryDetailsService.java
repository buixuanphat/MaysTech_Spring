package com.bxp.MaysTech_Spring.service;

import com.bxp.MaysTech_Spring.dto.delivery_details.DeliveryDetailsCreateRequest;
import com.bxp.MaysTech_Spring.dto.delivery_details.DeliveryDetailsResponse;
import com.bxp.MaysTech_Spring.entity.DeliveryDetail;
import com.bxp.MaysTech_Spring.exception.AppException;
import com.bxp.MaysTech_Spring.exception.MyApiResponse;
import com.bxp.MaysTech_Spring.repository.DeliveryDetailsRepository;
import com.bxp.MaysTech_Spring.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeliveryDetailsService {

    @Autowired
    DeliveryDetailsRepository deliveryDetailsRepository;

    @Autowired
    DeliveryRepository deliveryRepository;

    public DeliveryDetailsResponse getById(int id)
    {
        DeliveryDetail deliveryDetail = deliveryDetailsRepository.findById(id).orElseThrow(() -> new AppException(MyApiResponse.NOT_FOUND));
        DeliveryDetailsResponse response = new DeliveryDetailsResponse();
        response.setId(deliveryDetail.getId());
        response.setDeliveryId(deliveryDetail.getDelivery().getId());
        response.setProductId(deliveryDetail.getProductId());
        response.setName(deliveryDetail.getName());
        response.setImage(deliveryDetail.getImage());
        response.setTotalAmount(deliveryDetail.getTotalAmount());
        response.setTotalPrice(deliveryDetail.getTotalPrice());
        return response;

    }

    public List<DeliveryDetailsResponse> getDeliveringProduct (int deliveryId)
    {
        List<DeliveryDetail> deliveryDetails = deliveryDetailsRepository.findAllByDelivery_Id(deliveryId);
        return deliveryDetails.stream().map(d -> {
            DeliveryDetailsResponse response = new DeliveryDetailsResponse();
            response.setId(d.getId());
            response.setDeliveryId(d.getDelivery().getId());
            response.setProductId(d.getProductId());
            response.setName(d.getName());
            response.setImage(d.getImage());
            response.setTotalAmount(d.getTotalAmount());
            response.setTotalPrice(d.getTotalPrice());
            return response;
        }).collect(Collectors.toList());
    }

    public List<DeliveryDetailsResponse> addDeliveryDetails(int deliveryId, List<DeliveryDetailsCreateRequest> requests)
    {
        List<DeliveryDetailsResponse> responses = new ArrayList<>();

        requests.forEach(request -> {
            DeliveryDetail deliveryDetail = new DeliveryDetail();
            deliveryDetail.setDelivery(deliveryRepository.findById(request.getDeliveryId()).orElseThrow(() -> new AppException(MyApiResponse.NOT_FOUND)));
            deliveryDetail.setProductId(request.getProductId());
            deliveryDetail.setName(request.getName());
            deliveryDetail.setImage(request.getImage());
            deliveryDetail.setTotalAmount(request.getTotalAmount());
            deliveryDetail.setTotalPrice(request.getTotalPrice());
            deliveryDetailsRepository.save(deliveryDetail);
            responses.add(getById(deliveryDetail.getId()));
        });

        return getDeliveringProduct(deliveryId);
    }



}
