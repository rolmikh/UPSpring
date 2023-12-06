package com.example.apinew.Models;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.NotBlank;




@Entity
@Table(name = "Category_Product")
public class CategoryProduct {

        @Id
        @Column(name = "ID_Category_Product")
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long idCategoryProduct;

        @NotBlank(message = "Поле 'Название категории товара' должно быть заполнено!")
        @Column(name = "Name_Category_Product")
        private String NameCategoryProduct;


    public CategoryProduct(long idCategoryProduct, String nameCategoryProduct) {
        this.idCategoryProduct = idCategoryProduct;
        NameCategoryProduct = nameCategoryProduct;
    }

    public CategoryProduct() {
        }

        public CategoryProduct(String nameCategoryProduct) {
            NameCategoryProduct = nameCategoryProduct;
        }

        public long getIdCategoryProduct() {
            return idCategoryProduct;
        }

        public void setIdCategoryProduct(long idCategoryProduct) {
            this.idCategoryProduct = idCategoryProduct;
        }

        public String getNameCategoryProduct() {
            return NameCategoryProduct;
        }

        public void setNameCategoryProduct(String nameCategoryProduct) {
            NameCategoryProduct = nameCategoryProduct;
        }
}