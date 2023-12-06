package com.example.apinew.repository;

import com.example.apinew.Models.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<UsersModel, Long> {
    List<UsersModel> findByEmailUser (String emailUser);
}
