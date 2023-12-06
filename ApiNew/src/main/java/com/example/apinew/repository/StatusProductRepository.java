package com.example.apinew.repository;

import com.example.apinew.Models.StatusProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StatusProductRepository extends JpaRepository<StatusProduct, Long> {

    @Query("SELECT c FROM StatusProduct c WHERE c.NameStatusProduct = :nameStatusProduct")
    List<StatusProduct> findByNameStatusProduct(@Param("nameStatusProduct") String nameStatusProduct);
}
