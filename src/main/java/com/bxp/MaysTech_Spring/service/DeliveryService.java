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
        deliveryResponse.setHasFeedback(delivery.getHasFeedback());
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
            deliveryResponse.setTotalWeight(d.getTotalWeight());
            deliveryResponse.setHasFeedback(d.getHasFeedback());
            return deliveryResponse;
        }).collect(Collectors.toList());
    }

    public DeliveryResponse createDelivery(DeliveryCreateRequest request)
    {
        Delivery delivery = new Delivery();
        delivery.setUser(userService.getUserEntityById(request.getUserId()));
        delivery.setStartDate(LocalDate.now());
        delivery.setTotalPrice(request.getTotalPrice());
        delivery.setTotalAmount(request.getTotalAmount());
        delivery.setTotalWeight(request.getTotalWeight());
        delivery.setStatus(OrderStatus.PREPARING.getLabel());
        delivery.setHasFeedback(false);

        deliveryRepository.save(delivery);
        return this.getById(delivery.getId());
    }

    public List<DeliveryResponse> getDeliveryByStatus(int userId, String status)
    {
        return deliveryRepository.findAllByUser_IdAndStatus(userId, status).stream().map(d -> {
            DeliveryResponse response = new DeliveryResponse();
            response.setId(d.getId());
            response.setUserId(d.getUser().getId());
            response.setStatus(d.getStatus());
            response.setStartDate(d.getStartDate());
            response.setReceivedDate(d.getReceivedDate());
            response.setCancellationRequest(d.getCancellationRequest());
            response.setTotalPrice(d.getTotalPrice());
            response.setTotalAmount(d.getTotalAmount());
            response.setTotalWeight(d.getTotalWeight());
            response.setHasFeedback(d.getHasFeedback());
            return response;
        }).collect(Collectors.toList());
    }

    public DeliveryResponse updateFeedbackStatus(int id)
    {
        Delivery delivery = deliveryRepository.getById(id);
        delivery.setHasFeedback(true);
        deliveryRepository.save(delivery);
        return this.getById(delivery.getId());
    }



}
