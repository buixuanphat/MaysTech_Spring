package com.bxp.MaysTech_Spring.service;

import com.bxp.MaysTech_Spring.dto.brand.BrandRequest;
import com.bxp.MaysTech_Spring.entity.Brand;
import com.bxp.MaysTech_Spring.exception.AppException;
import com.bxp.MaysTech_Spring.exception.MyApiResponse;
import com.bxp.MaysTech_Spring.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {
    @Autowired
    BrandRepository brandRepository;

    public Brand createBrand(BrandRequest request)
    {
        if(brandRepository.existsByName(request.getName()))
        {
            throw new AppException(MyApiResponse.BRAND_ALREADY_EXISTS);
        }

        Brand brand = new Brand();
        brand.setName(request.getName());
        brand.setLogo(request.getLogo());
        return brandRepository.save(brand);
    }

    public List<Brand> getBrands()
    {
        return brandRepository.findAll();
    }

    public Brand getBrandById(int brandId)
    {
        return brandRepository.findById(brandId).orElseThrow(()->new AppException(MyApiResponse.NOT_FOUND));
    }

    public Brand updateBrand(int brandId, BrandRequest request)
    {
        if(!brandRepository.existsById(brandId))
        {
            throw new AppException(MyApiResponse.NOT_FOUND);
        }
        Brand brand = this.getBrandById(brandId);
        brand.setName(request.getName());
        brand.setLogo(request.getLogo());
        return this.brandRepository.save(brand);
    }

    public void deleteBrand(int brandId)
    {
        if(!brandRepository.existsById(brandId))
        {
            throw new AppException(MyApiResponse.NOT_FOUND);
        }
        brandRepository.deleteById(brandId);
    }

    public List<Brand> findBrandOfCategory(int catId)
    {
        return brandRepository.findBrandsOfCategory(catId);
    }

}
