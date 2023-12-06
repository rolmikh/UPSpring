package com.example.apinew.repository;


import com.example.apinew.Models.CategoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryProductRepository extends JpaRepository<CategoryProduct, Long> {

    @Query("SELECT c FROM CategoryProduct c WHERE c.NameCategoryProduct = :nameCategoryProduct")
    List<CategoryProduct> findByNameCategoryProduct(@Param("nameCategoryProduct") String nameCategoryProduct);
}
