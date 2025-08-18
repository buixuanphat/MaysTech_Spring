package com.bxp.MaysTech_Spring.service;

import com.bxp.MaysTech_Spring.dto.delivery.*;
import com.bxp.MaysTech_Spring.dto.delivery_details.DeliveryDetailsResponse;
import com.bxp.MaysTech_Spring.entity.Delivery;
import com.bxp.MaysTech_Spring.entity.ProductImage;
import com.bxp.MaysTech_Spring.repository.DeliveryRepository;
import com.bxp.MaysTech_Spring.repository.ProductImageRepository;
import com.bxp.MaysTech_Spring.repository.UserRepository;
import com.bxp.MaysTech_Spring.utils.STATIC;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeliveryService {

    @Autowired
    DeliveryRepository  deliveryRepository;

    @Autowired
    DeliveryDetailsService deliveryDetailsService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductImageRepository  productImageRepository;



    public DeliveryResponse createDelivery(DeliveryCreateRequest request)
    {
        Delivery delivery = new Delivery();
        delivery.setUser(userRepository.getById(request.getUserId()));
        delivery.setStartDate(LocalDate.now().toString());
        delivery.setReceivedDate(null);
        delivery.setStatus(STATIC.PREPARING);
        delivery.setCancellationRequest(false);
        delivery.setTotalPrice(request.getTotalPrice());
        delivery.setTotalAmount(request.getTotalAmount());
        delivery.setHasFeedback(false);
        delivery.setUsername(request.getUsername());
        delivery.setPhoneNumber(request.getPhoneNumber());
        delivery.setAddress(request.getAddress());

        return convertEntityToResponse(deliveryRepository.save(delivery));
    }





    public List<DeliveryWithFirstProductResponse> getDeliveryOfUserByStatus(int userId, String status)
    {
        return deliveryRepository.findAllByUser_IdAndStatus(userId, status).stream().map(
                delivery -> {
                    DeliveryDetailsResponse firstProduct = deliveryDetailsService.getFirstProduct(delivery.getId());
                    return convertEntityToResponseWithFirstProduct(delivery, firstProduct);
                }
        ).collect(Collectors.toList());
    }


    public List<DeliveryResponse> getDeliveryByStatus(String status)
    {
        return deliveryRepository.findAllByStatusOrderByCancellationRequestDesc(status).stream().map(this::convertEntityToResponse).toList();
    }


    public DeliveryWithFirstProductResponse updateFeedbackStatus(int id)
    {
        Delivery delivery = deliveryRepository.getById(id);
        delivery.setHasFeedback(true);
        deliveryRepository.save(delivery);
        return convertEntityToResponseWithFirstProduct(delivery, deliveryDetailsService.getFirstProduct(delivery.getId()));
    }

    public DeliveryResponse getDeliveryById(int id)
    {
        return convertEntityToResponse(deliveryRepository.getById(id));
    }





    public DeliveryWithFirstProductResponse requestCancelDelivery(int id)
    {
        Delivery delivery = deliveryRepository.getById(id);
        delivery.setCancellationRequest(true);
        deliveryRepository.save(delivery);
        return convertEntityToResponseWithFirstProduct(delivery, deliveryDetailsService.getFirstProduct(delivery.getId()));
    }





    public List<BestSellingResponse> getBestSellingProducts()
    {
        List<BestSellingResponse> bestSelling = deliveryRepository.getBestSellingProducts();
        bestSelling.forEach(product ->
        {
            ProductImage image = productImageRepository.getFirstByProduct_Id(product.getProductId());
            product.setProductImage(image.getImage());
        });
        return bestSelling;
    }





    public DeliveryResponse updateStatus(int id ,String status)
    {
        Delivery delivery = deliveryRepository.getById(id);
        delivery.setStatus(status);
        if(status.equalsIgnoreCase(STATIC.DELIVERED))
        {
            delivery.setReceivedDate(LocalDate.now().toString());
        }
        return convertEntityToResponse(deliveryRepository.save(delivery));
    }

    public DeliveryResponse ignore(int id)
    {
        Delivery delivery = deliveryRepository.getById(id);
        delivery.setCancellationRequest(false);
        return convertEntityToResponse(deliveryRepository.save(delivery));
    }


    public DeliveryResponse cancel(int id)
    {
        Delivery delivery = deliveryRepository.getById(id);
        delivery.setStatus(STATIC.CANCELED);
        return convertEntityToResponse(deliveryRepository.save(delivery));
    }


    public List<ProductRevenue> getProductRevenue()
    {
        return deliveryRepository.getProductRevenue();
    }

    public List<TimeRevenue> getDailyRevenue()
    {
        List<TimeRevenue> dailyRevenue = new ArrayList<>();
        for(Object[] row : deliveryRepository.getDailyRevenue()) {
            String time = (String) row[0];
            double revenue = ((Number) row[1]).doubleValue();
            dailyRevenue.add(new TimeRevenue(time, revenue));
        }
        return dailyRevenue;
    }

    public List<TimeRevenue> getMonthlyRevenue()
    {
        List<TimeRevenue> monthlyRevenue = new ArrayList<>();
        for(Object[] row : deliveryRepository.getMonthlyRevenue()) {
            String time = (String) row[0];
            double revenue = ((Number) row[1]).doubleValue();
            monthlyRevenue.add(new TimeRevenue(time, revenue));
        }
        return monthlyRevenue;
    }

    public List<TimeRevenue> getYearlyRevenue()
    {
        List<TimeRevenue> yearlyRevenue = new ArrayList<>();
        for(Object[] row : deliveryRepository.getYearlyRevenue()) {
            String time = (String) row[0];
            double revenue = ((Number) row[1]).doubleValue();
            yearlyRevenue.add(new TimeRevenue(time, revenue));
        }
        return yearlyRevenue;
    }

    public DeliveryWithFirstProductResponse convertEntityToResponseWithFirstProduct(Delivery delivery, DeliveryDetailsResponse deliveryDetail)
    {
        DeliveryWithFirstProductResponse response = new DeliveryWithFirstProductResponse();
        response.setId(delivery.getId());
        response.setUserId(delivery.getUser().getId());
        response.setStartDate(delivery.getStartDate());
        response.setReceivedDate(delivery.getReceivedDate());
        response.setStatus(delivery.getStatus());
        response.setCancellationRequest(delivery.getCancellationRequest());
        response.setTotalPrice(delivery.getTotalPrice());
        response.setTotalAmount(delivery.getTotalAmount());
        response.setHasFeedback(delivery.getHasFeedback());
        response.setUsername(delivery.getUsername());
        response.setPhoneNumber(delivery.getPhoneNumber());
        response.setAddress(delivery.getAddress());

        response.setProductId(deliveryDetail.getProductId());
        response.setProductName(deliveryDetail.getProductName());
        response.setProductImage(deliveryDetail.getProductImage());
        response.setProductTotalAmount(deliveryDetail.getTotalAmount());
        response.setProductTotalPrice(deliveryDetail.getTotalPrice());

        return  response;
    }

    public DeliveryResponse convertEntityToResponse(Delivery delivery)
    {
        DeliveryResponse deliveryResponse = new DeliveryResponse();
        deliveryResponse.setId(delivery.getId());
        deliveryResponse.setUserId(delivery.getUser().getId());
        deliveryResponse.setStartDate(delivery.getStartDate());
        deliveryResponse.setReceivedDate(delivery.getReceivedDate());
        deliveryResponse.setStatus(delivery.getStatus());
        deliveryResponse.setCancellationRequest(delivery.getCancellationRequest());
        deliveryResponse.setTotalPrice(delivery.getTotalPrice());
        deliveryResponse.setTotalAmount(delivery.getTotalAmount());
        deliveryResponse.setHasFeedback(delivery.getHasFeedback());
        deliveryResponse.setUsername(delivery.getUsername());
        deliveryResponse.setPhoneNumber(delivery.getPhoneNumber());
        deliveryResponse.setAddress(delivery.getAddress());
        return  deliveryResponse;
    }


}
