package com.example.prnew.Repositories;

import com.example.prnew.Models.CategoryProduct;
import com.example.prnew.Models.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryProductRepository extends JpaRepository<CategoryProduct, Long> {

    @Query("SELECT c FROM CategoryProduct c WHERE c.NameCategoryProduct = :nameCategoryProduct")
    CategoryProduct findByNameCategoryProduct(@Param("nameCategoryProduct") String nameCategoryProduct);
}
