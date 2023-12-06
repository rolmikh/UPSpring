package com.example.apinew.Models;

import org.hibernate.validator.constraints.NotBlank;

import jakarta.persistence.*;
@Entity
@Table(name = "Provider")
public class Provider {

    @Id
    @Column(name = "ID_Provider")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long IdProvider;

    @Column(name = "Name_Provider")
    @NotBlank(message = "Поле 'Название поставщика' должно быть заполнено!")
    private String NameProvider;

    @ManyToOne
    @JoinColumn(name = "Country_ID")
    private Country countries;


    public Provider(String nameProvider, Country countries) {
        NameProvider = nameProvider;
        this.countries = countries;
    }

    public Provider(long idProvider, String nameProvider, Country countries) {
        IdProvider = idProvider;
        NameProvider = nameProvider;
        this.countries = countries;
    }

    public Provider() {
    }

    public long getIdProvider() {
        return IdProvider;
    }

    public void setIdProvider(long idProvider) {
        IdProvider = idProvider;
    }

    public String getNameProvider() {
        return NameProvider;
    }

    public void setNameProvider(String nameProvider) {
        NameProvider = nameProvider;
    }

    public Country getCountries() {
        return countries;
    }

    public void setCountries(Country countries) {
        this.countries = countries;
    }
}
