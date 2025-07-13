package com.bxp.MaysTech_Spring.service;

import com.bxp.MaysTech_Spring.OrderStatus;
import com.bxp.MaysTech_Spring.dto.delivery.DeliveryCreateRequest;
import com.bxp.MaysTech_Spring.dto.delivery.DeliveryResponse;
import com.bxp.MaysTech_Spring.entity.Delivery;
import com.bxp.MaysTech_Spring.exception.AppException;
import com.bxp.MaysTech_Spring.exception.MyApiResponse;
import com.bxp.MaysTech_Spring.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeliveryService {

    @Autowired
    DeliveryRepository  deliveryRepository;

    @Autowired
    UserService userService;

    public DeliveryResponse getById(int id)
    {
        Delivery delivery = deliveryRepository.findById(id).orElseThrow(()-> new AppException(MyApiResponse.NOT_FOUND));
        DeliveryResponse deliveryResponse = new DeliveryResponse();
        deliveryResponse.setId(delivery.getId());
        deliveryResponse.setUserId(delivery.getUser().getId());
        deliveryResponse.setStatus(delivery.getStatus());
        deliveryResponse.setStartDate(delivery.getStartDate());
        deliveryResponse.setReceivedDate(delivery.getReceivedDate());
        deliveryResponse.setCancellationRequest(delivery.getCancellationRequest());
        deliveryResponse.setTotalPrice(delivery.getTotalPrice());
        deliveryResponse.setTotalAmount(delivery.getTotalAmount());
        return deliveryResponse;
    }

    public List<DeliveryResponse> getDeliveriesByUserId(int userId)
    {
        List<Delivery> deliveries = deliveryRepository.findAllByUser_Id(userId);
        return deliveries.stream().map(d -> {
            DeliveryResponse deliveryResponse = new DeliveryResponse();
            deliveryResponse.setId(d.getId());
            deliveryResponse.setUserId(d.getUser().getId());
            deliveryResponse.setStatus(d.getStatus());
            deliveryResponse.setStartDate(d.getStartDate());
            deliveryResponse.setReceivedDate(d.getReceivedDate());
            deliveryResponse.setCancellationRequest(d.getCancellationRequest());
            deliveryResponse.setTotalPrice(d.getTotalPrice());
            deliveryResponse.setTotalAmount(d.getTotalAmount());
            return deliveryResponse;
        }).collect(Collectors.toList());
    }

    public DeliveryResponse createDelivery(DeliveryCreateRequest request)
    {
        Delivery delivery = new Delivery();
        delivery.setUser(userService.getUserEntityById(request.getUserId()));
        delivery.setStartDate(LocalDate.now());
        delivery.setStatus(OrderStatus.PREPARING.getLabel());
        delivery.setCancellationRequest(false);
        delivery.setTotalPrice(request.getTotalPrice());
        delivery.setTotalAmount(request.getTotalAmount());

        deliveryRepository.save(delivery);
        return this.getById(delivery.getId());
    }



}
