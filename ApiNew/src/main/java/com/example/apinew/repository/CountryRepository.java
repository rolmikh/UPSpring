package com.example.apinew.repository;


import com.example.apinew.Models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long> {

    @Query("SELECT c FROM Country c WHERE c.NameCountry = :nameCountry")
    List<Country> findByNameCountry(@Param("nameCountry") String nameCountry);
}
