package com.example.prnew.Repositories;

import com.example.prnew.Models.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UsersModel, Long> {
    UsersModel findByEmailUser (String emailUser);
}
