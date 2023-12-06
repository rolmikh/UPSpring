package com.example.apinew.Models;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "Orders")
public class Orders {

    @Id
    @Column(name = "ID_Order")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long IdOrder;

    @Column(name = "Price_Order")
    private int PriceOrder;

    @NotBlank(message = "Поле 'Номер заказа' должно быть заполнено!")
    @Column(name = "Number_Order")
    private String NumberOrder;

    @ManyToOne
    @JoinColumn(name = "Status_Order_ID")
    private StatusOrder statusOrder;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "User_ID", referencedColumnName = "ID_User", nullable = true),
            @JoinColumn(name = "Role_ID", referencedColumnName = "rolee_id", nullable = true)
    })
    private UsersModel users;


    @NotBlank(message = "Поле 'Дата заказа' должно быть заполнено!")
    @Column(name = "Date_Order")
    private String DateOrder;

//    @ManyToMany
//    @JoinTable(name = "Order_List",
//            joinColumns = {@JoinColumn(name = "Order_ID")},
//            inverseJoinColumns = {@JoinColumn(name = "Product_ID")})
//    private Set<Product> products = new HashSet<>();


    public Orders() {
    }


    public Orders(int priceOrder, String numberOrder, StatusOrder statusOrder, UsersModel users, String dateOrder, Set<Product> products) {
        PriceOrder = priceOrder;
        NumberOrder = numberOrder;
        this.statusOrder = statusOrder;
        this.users = users;
        DateOrder = dateOrder;
//        this.products = products;
    }

    public Orders(long idOrder, int priceOrder, String numberOrder, StatusOrder statusOrder, UsersModel users, String dateOrder) {
        IdOrder = idOrder;
        PriceOrder = priceOrder;
        NumberOrder = numberOrder;
        this.statusOrder = statusOrder;
        this.users = users;
        DateOrder = dateOrder;
    }

    public long getIdOrder() {
        return IdOrder;
    }

    public void setIdOrder(long idOrder) {
        IdOrder = idOrder;
    }

    public int getPriceOrder() {
        return PriceOrder;
    }

    public void setPriceOrder(int priceOrder) {
        PriceOrder = priceOrder;
    }

    public String getNumberOrder() {
        return NumberOrder;
    }

    public void setNumberOrder(String numberOrder) {
        NumberOrder = numberOrder;
    }

    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
    }

    public UsersModel getUsers() {
        return users;
    }

    public void setUsers(UsersModel users) {
        this.users = users;
    }

    public String getDateOrder() {
        return DateOrder;
    }

    public void setDateOrder(String dateOrder) {
        DateOrder = dateOrder;
    }

//    public Set<Product> getProducts() {
//        return products;
//    }
//
//    public void setProducts(Set<Product> products) {
//        this.products = products;
//    }
}
