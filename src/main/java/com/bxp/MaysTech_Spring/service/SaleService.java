package com.bxp.MaysTech_Spring.service;

import com.bxp.MaysTech_Spring.dto.ApiResponse;
import com.bxp.MaysTech_Spring.dto.sale.SaleResponse;
import com.bxp.MaysTech_Spring.entity.Sale;
import com.bxp.MaysTech_Spring.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleService {

    @Autowired
    SaleRepository saleRepository;

    public SaleResponse getSaleById(int id)
    {
        return convertEntityToResponse(saleRepository.getById(id));
    }


    public SaleResponse createSale(double percent){
        Sale sale = new Sale();
        sale.setPercent(percent);
        return convertEntityToResponse(saleRepository.save(sale));
    }

    public SaleResponse updateSale(int id , double percent){
        Sale sale = saleRepository.getById(id);
        sale.setPercent(percent);
        return convertEntityToResponse(saleRepository.save(sale));
    }

    public SaleResponse deleteSale(int id){
        Sale sale = saleRepository.getById(id);
        saleRepository.delete(sale);
        return convertEntityToResponse(sale);
    }

    public List<SaleResponse> getAllSales(){
        return saleRepository.findAll().stream().map(this::convertEntityToResponse).collect(Collectors.toList());
    }

    SaleResponse convertEntityToResponse(Sale sale){
        SaleResponse saleResponse = new SaleResponse();
        saleResponse.setId(sale.getId());
        saleResponse.setPercent(sale.getPercent());
        return saleResponse;
    }

}
