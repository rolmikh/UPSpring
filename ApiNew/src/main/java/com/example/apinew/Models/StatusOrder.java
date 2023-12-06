package com.example.apinew.Models;

import org.hibernate.validator.constraints.NotBlank;

import jakarta.persistence.*;
@Entity
@Table(name = "Status_Order")
public class StatusOrder {

    @Id
    @Column(name = "ID_Status_Order")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long IdStatusOrder;

    @NotBlank(message = "Поле Название статуса заказа должно быть заполнено")
    @Column(name = "Name_Status_Order")
    private String NameStatusOrder;


    public StatusOrder(String nameStatusOrder) {
        NameStatusOrder = nameStatusOrder;
    }

    public StatusOrder() {
    }

    public StatusOrder(long idStatusOrder, String nameStatusOrder) {
        IdStatusOrder = idStatusOrder;
        NameStatusOrder = nameStatusOrder;
    }

    public long getIdStatusOrder() {
        return IdStatusOrder;
    }

    public void setIdStatusOrder(long idStatusOrder) {
        IdStatusOrder = idStatusOrder;
    }

    public String getNameStatusOrder() {
        return NameStatusOrder;
    }

    public void setNameStatusOrder(String nameStatusOrder) {
        NameStatusOrder = nameStatusOrder;
    }
}
