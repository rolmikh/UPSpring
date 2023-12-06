package com.example.apinew.repository;

import com.example.apinew.Models.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
    @Query("SELECT c FROM Provider c WHERE c.NameProvider = :nameProvider")
    List<Provider> findByNameProvider(@Param("nameProvider") String nameProvider);
}
