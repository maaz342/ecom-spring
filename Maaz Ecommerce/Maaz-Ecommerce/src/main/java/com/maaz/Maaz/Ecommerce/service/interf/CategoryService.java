package com.maaz.Maaz.Ecommerce.service.interf;

import com.maaz.Maaz.Ecommerce.dto.CategoryDto;
import com.maaz.Maaz.Ecommerce.dto.Response;

public interface CategoryService {
    Response createCategory(CategoryDto categoryDto);
    Response updateCategory(Long categoryId,CategoryDto categoryDto);
    Response getAllCategories();
    Response getCategoryById(Long categoryId);
    Response deleteCategory(Long categoryId);


}
