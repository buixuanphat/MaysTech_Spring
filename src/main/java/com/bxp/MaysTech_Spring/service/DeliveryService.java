package com.bxp.MaysTech_Spring.service;

import com.bxp.MaysTech_Spring.OrderStatus;
import com.bxp.MaysTech_Spring.dto.delivery.DeliveryCreateRequest;
import com.bxp.MaysTech_Spring.dto.delivery.DeliveryResponse;
import com.bxp.MaysTech_Spring.entity.Delivery;
import com.bxp.MaysTech_Spring.repository.DeliveryRepository;
import com.bxp.MaysTech_Spring.repository.UserRepository;
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
    UserRepository userRepository;

    public DeliveryResponse getById(int id)
    {
        Delivery delivery = deliveryRepository.getById(id);
        return convertEntityToResponse(delivery);
    }

    public List<DeliveryResponse> getDeliveriesByUserId(int userId)
    {
        List<Delivery> deliveries = deliveryRepository.findAllByUser_Id(userId);
        return deliveries.stream().map(this::convertEntityToResponse).collect(Collectors.toList());
    }

    public List<DeliveryResponse> getDeliveryOfUserByStatus(int userId, String status)
    {
        return deliveryRepository.findAllByUser_IdAndStatus(userId, status).stream().map(this::convertEntityToResponse).collect(Collectors.toList());
    }

    public DeliveryResponse createDelivery(DeliveryCreateRequest request)
    {
        Delivery delivery = new Delivery();
        delivery.setUser(userRepository.getById(request.getUserId()));
        delivery.setStartDate(LocalDate.now());
        delivery.setTotalPrice(request.getTotalPrice());
        delivery.setTotalAmount(request.getTotalAmount());
        delivery.setStatus(OrderStatus.PREPARING.getLabel());
        delivery.setHasFeedback(false);
        delivery.setCancellationRequest(false);

        deliveryRepository.save(delivery);
        return this.getById(delivery.getId());
    }



    public DeliveryResponse updateFeedbackStatus(int id)
    {
        Delivery delivery = deliveryRepository.getById(id);
        delivery.setHasFeedback(true);
        deliveryRepository.save(delivery);
        return this.getById(delivery.getId());
    }

    public DeliveryResponse updateDeliveryStatus(int id, String status)
    {
        Delivery delivery = deliveryRepository.getById(id);
        delivery.setStatus(status);
        deliveryRepository.save(delivery);
        return this.getById(delivery.getId());
    }

    public DeliveryResponse requestCancelDelivery(int id)
    {
        Delivery delivery = deliveryRepository.getById(id);
        delivery.setCancellationRequest(true);
        deliveryRepository.save(delivery);
        return this.getById(delivery.getId());
    }

    public List<DeliveryResponse> getAllDeliveriesByStatus(String status)
    {
        List<Delivery> deliveries = deliveryRepository.getAllByStatus(status);
        return deliveries.stream().map(this::convertEntityToResponse).toList();
    }

    public DeliveryResponse convertEntityToResponse(Delivery delivery)
    {
        DeliveryResponse deliveryResponse = new DeliveryResponse();
        deliveryResponse.setId(delivery.getId());
        deliveryResponse.setUserId(delivery.getUser().getId());
        deliveryResponse.setStatus(delivery.getStatus());
        deliveryResponse.setStartDate(delivery.getStartDate());
        deliveryResponse.setReceivedDate(delivery.getReceivedDate());
        deliveryResponse.setCancellationRequest(delivery.getCancellationRequest());
        deliveryResponse.setTotalPrice(delivery.getTotalPrice());
        deliveryResponse.setTotalAmount(delivery.getTotalAmount());
        deliveryResponse.setAddress(delivery.getAddress());
        return  deliveryResponse;
    }


}
