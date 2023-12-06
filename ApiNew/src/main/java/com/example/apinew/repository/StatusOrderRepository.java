package com.example.apinew.repository;

import com.example.apinew.Models.StatusOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StatusOrderRepository extends JpaRepository<StatusOrder, Long> {
    @Query("SELECT c FROM StatusOrder c WHERE c.NameStatusOrder = :nameStatusOrder")
    List<StatusOrder> findByNameStatusOrder(@Param("nameStatusOrder") String nameStatusOrder);
}
