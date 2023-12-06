package com.example.prnew.Repositories;

import com.example.prnew.Models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CountryRepository extends JpaRepository<Country, Long> {

    @Query("SELECT c FROM Country c WHERE c.NameCountry = :nameCountry")
    Country findByNameCountry(@Param("nameCountry") String nameCountry);
}
