package com.example.prnew.Models;

import javax.persistence.*;

@Entity
@Table(name = "Order_List")
public class Orderlist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_Order_List")
    private long IdOrderList;


    @ManyToOne
    @JoinColumn(name = "Product_ID", nullable = false)
    private Product product;


    @ManyToOne
    @JoinColumn(name = "Order_ID", nullable = false)
    private Orders orders;

    public Orderlist() {
    }

    public Orderlist(Product product, Orders orders) {
        this.product = product;
        this.orders = orders;
    }

    public long getIdOrderList() {
        return IdOrderList;
    }

    public void setIdOrderList(long idOrderList) {
        IdOrderList = idOrderList;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }
}
