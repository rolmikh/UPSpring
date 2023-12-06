package com.example.prnew.Models;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Product")
public class Product {

    @Id
    @Column(name = "ID_Product")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long IdProduct;

    @NotBlank(message = "Поле 'Название товара' должно быть заполнено!")
    @Column(name = "Name_Product")
    private String NameProduct;

//    @NotBlank(message = "Поле 'Стоимость товара' должно быть заполнено!")
    @Column(name = "Price_Product")
    private int PriceProduct;
//    @NotBlank(message = "Поле 'Количество товара' должно быть заполнено!")
    @Column(name = "Count_Product")
    private int CountProduct;

    @ManyToOne
    @JoinColumn(name = "Category_Product_ID")
    private CategoryProduct categoryProduct;

    @ManyToOne
    @JoinColumn(name = "Provider_ID")
    private Provider provider;

    @ManyToOne
    @JoinColumn(name = "Status_Product_ID")
    private StatusProduct statusProduct;


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Orderlist> orderLists;

    public Product() {
    }

    public Product(String nameProduct, int priceProduct, int countProduct, CategoryProduct categoryProduct, Provider provider, StatusProduct statusProduct) {
        NameProduct = nameProduct;
        PriceProduct = priceProduct;
        CountProduct = countProduct;
        this.categoryProduct = categoryProduct;
        this.provider = provider;
        this.statusProduct = statusProduct;
    }

    public Product(long idProduct, String nameProduct, int priceProduct, int countProduct, CategoryProduct categoryProduct, Provider provider, StatusProduct statusProduct) {
        IdProduct = idProduct;
        NameProduct = nameProduct;
        PriceProduct = priceProduct;
        CountProduct = countProduct;
        this.categoryProduct = categoryProduct;
        this.provider = provider;
        this.statusProduct = statusProduct;
    }

    public long getIdProduct() {
        return IdProduct;
    }

    public void setIdProduct(long idProduct) {
        IdProduct = idProduct;
    }

    public String getNameProduct() {
        return NameProduct;
    }

    public void setNameProduct(String nameProduct) {
        NameProduct = nameProduct;
    }

    public int getPriceProduct() {
        return PriceProduct;
    }

    public void setPriceProduct(int priceProduct) {
        PriceProduct = priceProduct;
    }

    public int getCountProduct() {
        return CountProduct;
    }

    public void setCountProduct(int countProduct) {
        CountProduct = countProduct;
    }

    public CategoryProduct getCategoryProduct() {
        return categoryProduct;
    }

    public void setCategoryProduct(CategoryProduct categoryProduct) {
        this.categoryProduct = categoryProduct;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public StatusProduct getStatusProduct() {
        return statusProduct;
    }

    public void setStatusProduct(StatusProduct statusProduct) {
        this.statusProduct = statusProduct;
    }


}
