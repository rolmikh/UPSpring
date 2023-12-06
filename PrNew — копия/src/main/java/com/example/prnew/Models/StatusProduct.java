package com.example.prnew.Models;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

@Entity
@Table(name = "Status_Product")
public class StatusProduct {

    @Id
    @Column(name = "ID_Status_Product")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long IdStatusProduct;

    @NotBlank(message = "Поле Название статуса товара должно быть заполнено")
    @Column(name = "Name_Status_Product")
    private String NameStatusProduct;

    public StatusProduct() {
    }

    public StatusProduct(String nameStatusProduct) {
        NameStatusProduct = nameStatusProduct;
    }

    public long getIdStatusProduct() {
        return IdStatusProduct;
    }

    public void setIdStatusProduct(long idStatusProduct) {
        IdStatusProduct = idStatusProduct;
    }

    public String getNameStatusProduct() {
        return NameStatusProduct;
    }

    public void setNameStatusProduct(String nameStatusProduct) {
        NameStatusProduct = nameStatusProduct;
    }
}
