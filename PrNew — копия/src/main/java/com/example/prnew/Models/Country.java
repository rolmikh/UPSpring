package com.example.prnew.Models;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

@Entity
@Table(name = "Country")
public class Country {

    @Id
    @Column(name = "ID_Country")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long IdCountry;
    @NotBlank(message = "Поле 'Страна' должно быть заполнено")
    @Column(name = "Name_Country")
    private String NameCountry;

    public Country() {
    }

    public Country(long idCountry, String nameCountry) {
        IdCountry = idCountry;
        NameCountry = nameCountry;
    }

    public Country(String nameCountry) {
        NameCountry = nameCountry;
    }

    public long getIdCountry() {
        return IdCountry;
    }

    public void setIdCountry(long idCountry) {
        IdCountry = idCountry;
    }

    public String getNameCountry() {
        return NameCountry;
    }

    public void setNameCountry(String nameCountry) {
        NameCountry = nameCountry;
    }
}
