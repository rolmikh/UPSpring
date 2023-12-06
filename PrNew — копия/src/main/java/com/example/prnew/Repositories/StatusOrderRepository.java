package com.example.prnew.Repositories;

import com.example.prnew.Models.StatusOrder;
import com.example.prnew.Models.StatusProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StatusOrderRepository extends JpaRepository<StatusOrder, Long> {
    @Query("SELECT c FROM StatusOrder c WHERE c.NameStatusOrder = :nameStatusOrder")
    StatusOrder findByNameStatusOrder(@Param("nameStatusOrder") String nameStatusOrder);
}
