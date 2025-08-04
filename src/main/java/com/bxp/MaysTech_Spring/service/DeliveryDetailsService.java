package com.bxp.MaysTech_Spring.service;

import com.bxp.MaysTech_Spring.dto.delivery_details.DeliveryDetailsCreateRequest;
import com.bxp.MaysTech_Spring.dto.delivery_details.DeliveryDetailsResponse;
import com.bxp.MaysTech_Spring.entity.DeliveryDetail;
import com.bxp.MaysTech_Spring.exception.AppException;
import com.bxp.MaysTech_Spring.exception.MyApiResponse;
import com.bxp.MaysTech_Spring.repository.DeliveryDetailsRepository;
import com.bxp.MaysTech_Spring.repository.DeliveryRepository;
import com.bxp.MaysTech_Spring.repository.ProductRepository;
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

    @Autowired
    ProductRepository productRepository;

    public DeliveryDetailsResponse getById(int id)
    {
        DeliveryDetail deliveryDetail = deliveryDetailsRepository.findById(id).orElseThrow(() -> new AppException(MyApiResponse.NOT_FOUND));
        return convertEntityToResponse(deliveryDetail);
    }

    public List<DeliveryDetailsResponse> getProductInDelivery(int deliveryId)
    {
        List<DeliveryDetail> deliveryDetails = deliveryDetailsRepository.findAllByDelivery_Id(deliveryId);
        return deliveryDetails.stream().map(this::convertEntityToResponse).collect(Collectors.toList());
    }

    public List<DeliveryDetailsResponse> addDeliveryDetails(int deliveryId, List<DeliveryDetailsCreateRequest> requests)
    {
        List<DeliveryDetailsResponse> responses = new ArrayList<>();

        requests.forEach(request -> {
            DeliveryDetail deliveryDetail = new DeliveryDetail();
            deliveryDetail.setDelivery(deliveryRepository.getById(deliveryId));
            deliveryDetail.setProduct(productRepository.getReferenceById(request.getProductId()));
            deliveryDetail.setTotalAmount(request.getTotalAmount());
            deliveryDetail.setTotalPrice(request.getTotalPrice());
            deliveryDetailsRepository.save(deliveryDetail);
            responses.add(getById(deliveryDetail.getId()));
        });

        return getProductInDelivery(deliveryId);
    }

    DeliveryDetailsResponse convertEntityToResponse(DeliveryDetail deliveryDetail)
    {
        DeliveryDetailsResponse response = new DeliveryDetailsResponse();
        response.setId(deliveryDetail.getId());
        response.setDeliveryId(deliveryDetail.getDelivery().getId());
        response.setProductId(deliveryDetail.getProduct().getId());
        response.setProductName(deliveryDetail.getProduct().getName());
        response.setProductPrice(deliveryDetail.getProduct().getPrice());
        response.setProductImage(deliveryDetail.getProduct().getImage());
        response.setTotalAmount(deliveryDetail.getTotalAmount());
        response.setTotalPrice(deliveryDetail.getTotalPrice());
        return response;
    }


}
