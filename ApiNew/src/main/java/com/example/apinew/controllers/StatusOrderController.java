package com.example.apinew.controllers;

import com.example.apinew.Models.StatusOrder;
import com.example.apinew.repository.StatusOrderRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StatusOrderController {

    @Autowired
    private StatusOrderRepository statusOrderRepository;



    @GetMapping("/statusOrder")
    public ResponseEntity<List<StatusOrder>> getStatusOrder(){
        List<StatusOrder> statusOrders = statusOrderRepository.findAll();

        return new ResponseEntity<>(statusOrders, HttpStatus.OK);
    }

    @GetMapping("/statusOrder/{idStatusOrder}")
    public ResponseEntity<StatusOrder> oneStatusOrder(@PathVariable Long idStatusOrder){
        Optional<StatusOrder> optionalStatusOrder = statusOrderRepository.findById(idStatusOrder);

        if(optionalStatusOrder.isPresent()){
            return new ResponseEntity<>(optionalStatusOrder.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/statusOrder")
    public ResponseEntity<StatusOrder> createStatusOrder(@Valid @RequestBody StatusOrder statusOrder){
        return new ResponseEntity<>(statusOrderRepository.save(statusOrder), HttpStatus.CREATED);
    }

    @PutMapping("/statusOrder/{idStatusOrder}")
    public ResponseEntity<StatusOrder> updateStatusOrder(@PathVariable Long idStatusOrder,
                                           @Valid @RequestBody StatusOrder statusOrder){
        Optional<StatusOrder> optionalStatusOrder = statusOrderRepository.findById(idStatusOrder);

        if(optionalStatusOrder.isEmpty()){
            return new ResponseEntity<>(optionalStatusOrder.get(), HttpStatus.OK);
        }

        StatusOrder statusOrder1 = optionalStatusOrder.get();
        statusOrder1.setIdStatusOrder(idStatusOrder);
        statusOrder1.setNameStatusOrder(statusOrder.getNameStatusOrder());

        return new ResponseEntity<>(statusOrderRepository.save(statusOrder1), HttpStatus.OK);
    }

    @DeleteMapping("/statusOrder/{idStatusOrder}")
    public ResponseEntity<?> deleteStatusOrder(@PathVariable Long idStatusOrder){
        Optional<StatusOrder> optionalStatusOrder = statusOrderRepository.findById(idStatusOrder);

        if(optionalStatusOrder.isEmpty()){
            return new ResponseEntity<>(optionalStatusOrder.get(), HttpStatus.OK);
        }
        StatusOrder statusOrder1 = optionalStatusOrder.get();

        statusOrderRepository.delete(statusOrder1);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
