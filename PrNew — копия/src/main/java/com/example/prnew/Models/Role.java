package com.example.prnew.Models;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

@Entity
@Table(name = "Role")
public class Role {

    @Id
    @Column(name = "ID_Role")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idRole;
    @NotBlank(message = "Поле 'Название роли' должно быть заполнено!")
    @Column(name = "Name_Role")
    private String NameRole;

    @Column(name = "Role")
    private String role;

    public Role() {
    }

    public Role(long idRole, String nameRole) {
        this.idRole = idRole;
        NameRole = nameRole;
    }

    public Role(String nameRole) {
        NameRole = nameRole;
    }

    public long getIdRole() {
        return idRole;
    }

    public void setIdRole(long idRole) {
        this.idRole = idRole;
    }

    public String getNameRole() {
        return NameRole;
    }

    public void setNameRole(String nameRole) {
        NameRole = nameRole;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
