package com.example.apinew.controllers;

import com.example.apinew.Models.Orders;
import com.example.apinew.repository.OrdersRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class OrdersController {

    @Autowired
    private OrdersRepository ordersRepository;



    @GetMapping("/order")
    public ResponseEntity<List<Orders>> getOrders(){
        List<Orders> orders = ordersRepository.findAll();

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/order/{idOrder}")
    public ResponseEntity<Orders> oneOrder(@PathVariable Long idOrder){
        Optional<Orders> optionalOrder = ordersRepository.findById(idOrder);

        if(optionalOrder.isPresent()){
            return new ResponseEntity<>(optionalOrder.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/order")
    public ResponseEntity<Orders> createOrders(@Valid @RequestBody Orders orders){
        return new ResponseEntity<>(ordersRepository.save(orders), HttpStatus.CREATED);
    }

    @PutMapping("/order/{idOrder}")
    public ResponseEntity<Orders> updateOrders(@PathVariable Long idOrders,
                                                 @Valid @RequestBody Orders orders){
        Optional<Orders> optionalOrders = ordersRepository.findById(idOrders);

        if(optionalOrders.isEmpty()){
            return new ResponseEntity<>(optionalOrders.get(), HttpStatus.OK);
        }

        Orders orders1 = optionalOrders.get();
        orders1.setIdOrder(idOrders);
        orders1.setNumberOrder(orders.getNumberOrder());
        orders1.setDateOrder(orders.getDateOrder());
        orders1.setPriceOrder(orders.getPriceOrder());
        orders1.setStatusOrder(orders.getStatusOrder());
        orders1.setUsers(orders.getUsers());

        return new ResponseEntity<>(ordersRepository.save(orders1), HttpStatus.OK);
    }

    @DeleteMapping("/order/{idOrder}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long idOrder){
        Optional<Orders> optionalOrder = ordersRepository.findById(idOrder);

        if(optionalOrder.isEmpty()){
            return new ResponseEntity<>(optionalOrder.get(), HttpStatus.OK);
        }
        Orders orders1 = optionalOrder.get();

        ordersRepository.delete(orders1);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
