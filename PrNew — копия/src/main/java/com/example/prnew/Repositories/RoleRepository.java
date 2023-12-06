package com.example.prnew.Repositories;

import com.example.prnew.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRole (String Role);
}
