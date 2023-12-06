package com.example.prnew.Models;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Users")
public class UsersModel {

    @Id
    @Column(name = "ID_User")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long IdUsers;

    @NotBlank(message = "Поле 'Фамилия' должно быть заполнено!")
    @Column(name = "Surname")
    private String Surname;
    @NotBlank(message = "Поле 'Имя' должно быть заполнено!")
    @Column(name = "First_Name")
    private String FirstName;

    @NotBlank(message = "Поле 'Отчество' должно быть заполнено!")
    @Column(name = "Middle_Name")
    private String MiddleName;

    @NotBlank(message = "Поле 'Электронная почта' должно быть заполнено!")
    @Column(name = "Email_User")
    private String emailUser;

    @NotBlank(message = "Поле 'Пароль' должно быть заполнено!")
    @Column(name = "Password_User")
    private String PasswordUser;

    @Column(name = "active", nullable = false, length = 32)
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "Role_ID")
    private Role roleID;
    @ElementCollection(targetClass = RoleEnum.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "Users", joinColumns = @JoinColumn(name = "Role_ID"))
    @Enumerated(EnumType.STRING)
    private Set<RoleEnum> role;


    public UsersModel() {
    }

    public UsersModel(String surname, String firstName, String middleName, String emailUser, String passwordUser, boolean active, Role roleID) {
        Surname = surname;
        FirstName = firstName;
        MiddleName = middleName;
        this.emailUser = emailUser;
        PasswordUser = passwordUser;
        this.active = active;
        this.roleID = roleID;
    }


    public UsersModel(long idUsers, String surname, String firstName, String middleName, String emailUser, String passwordUser, boolean active, Role roleID) {
        IdUsers = idUsers;
        Surname = surname;
        FirstName = firstName;
        MiddleName = middleName;
        this.emailUser = emailUser;
        PasswordUser = passwordUser;
        this.active = active;
        this.roleID = roleID;
    }

    public long getIdUsers() {
        return IdUsers;
    }

    public void setIdUsers(long idUsers) {
        IdUsers = idUsers;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public void setMiddleName(String middleName) {
        MiddleName = middleName;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getPasswordUser() {
        return PasswordUser;
    }

    public void setPasswordUser(String passwordUser) {
        PasswordUser = passwordUser;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Role getRoleID() {
        return roleID;
    }

    public void setRoleID(Role roleID) {
        this.roleID = roleID;
    }

    public Set<RoleEnum> getRole() {
        return role;
    }

    public void setRole(Set<RoleEnum> role) {
        this.role = role;
    }
}
