package com.example.prnew.Repositories;

import com.example.prnew.Models.StatusProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StatusProductRepository extends JpaRepository<StatusProduct, Long> {

    @Query("SELECT c FROM StatusProduct c WHERE c.NameStatusProduct = :nameStatusProduct")
    StatusProduct findByNameStatusProduct(@Param("nameStatusProduct") String nameStatusProduct);
}
