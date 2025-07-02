package com.bxp.MaysTech_Spring.service;

import com.bxp.MaysTech_Spring.dto.category.CategoryRequest;
import com.bxp.MaysTech_Spring.entity.Category;
import com.bxp.MaysTech_Spring.exception.AppException;
import com.bxp.MaysTech_Spring.exception.MyApiResponse;
import com.bxp.MaysTech_Spring.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Category createCategory(CategoryRequest request)
    {
        if(categoryRepository.existsByName(request.getName()))
        {
            throw new AppException(MyApiResponse.CATEGORY_ALREADY_EXISTS);
        }
        Category category = new Category();
        category.setName(request.getName());
        return categoryRepository.save(category);
    }

    public List<Category> getCategory()
    {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(int id)
    {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new AppException(MyApiResponse.NOT_FOUND));
    }

    public Category updateCategory(int catId, CategoryRequest request)
    {
        Category updatedCat = this.getCategoryById(catId);
        updatedCat.setName(request.getName());
        return categoryRepository.save(updatedCat);
    }

    public void  deleteCategory(int catId)
    {
        if(!categoryRepository.existsById(catId))
        {
            throw new AppException(MyApiResponse.NOT_FOUND);
        }
        categoryRepository.deleteById(catId);
    }
}
