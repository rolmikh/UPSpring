package com.example.prnew.Repositories;

import com.example.prnew.Models.Country;
import com.example.prnew.Models.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
    @Query("SELECT c FROM Provider c WHERE c.NameProvider = :nameProvider")
    Provider findByNameProvider(@Param("nameProvider") String nameProvider);
}
