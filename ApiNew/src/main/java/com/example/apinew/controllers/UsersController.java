package com.example.apinew.controllers;

import com.example.apinew.Models.UsersModel;
import com.example.apinew.repository.UsersRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/user")
    public ResponseEntity<List<UsersModel>> getOrders(){
        List<UsersModel> user = usersRepository.findAll();

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/user/{idUser}")
    public ResponseEntity<UsersModel> oneUser(@PathVariable Long idUser){
        Optional<UsersModel> optionalUser = usersRepository.findById(idUser);

        if(optionalUser.isPresent()){
            return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/user")
    public ResponseEntity<UsersModel> createUsers(@Valid @RequestBody UsersModel users){
        return new ResponseEntity<>(usersRepository.save(users), HttpStatus.CREATED);
    }


    @DeleteMapping("/user/{idUser}")
    public ResponseEntity<?> deleteUser(@PathVariable Long idUser){
        Optional<UsersModel> optionalUser = usersRepository.findById(idUser);

        if(optionalUser.isEmpty()){
            return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
        }
        UsersModel users1 = optionalUser.get();

        usersRepository.delete(users1);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
