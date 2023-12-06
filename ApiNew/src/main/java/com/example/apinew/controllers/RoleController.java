package com.example.apinew.controllers;

import com.example.apinew.Models.Role;
import com.example.apinew.repository.RoleRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;



    @GetMapping("/role")
    public ResponseEntity<List<Role>> getRole(){
        List<Role> roles = roleRepository.findAll();

        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("/role/{idRole}")
    public ResponseEntity<Role> oneRole(@PathVariable Long idRole){
        Optional<Role> optionalRole = roleRepository.findById(idRole);

        if(optionalRole.isPresent()){
            return new ResponseEntity<>(optionalRole.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/role")
    public ResponseEntity<Role> createRole(@Valid @RequestBody Role role){
        return new ResponseEntity<>(roleRepository.save(role), HttpStatus.CREATED);
    }

    @PutMapping("/role/{idRole}")
    public ResponseEntity<Role> updateRole(@PathVariable Long idRole,
                                                 @Valid @RequestBody Role role){
        Optional<Role> optionalRole = roleRepository.findById(idRole);

        if(optionalRole.isEmpty()){
            return new ResponseEntity<>(optionalRole.get(), HttpStatus.OK);
        }

        Role role1 = optionalRole.get();
        role1.setIdRole(idRole);
        role1.setNameRole(role.getNameRole());

        return new ResponseEntity<>(roleRepository.save(role1), HttpStatus.OK);
    }

    @DeleteMapping("/role/{idRole}")
    public ResponseEntity<?> deleteRole(@PathVariable Long idRole){
        Optional<Role> optionalRole = roleRepository.findById(idRole);

        if(optionalRole.isEmpty()){
            return new ResponseEntity<>(optionalRole.get(), HttpStatus.OK);
        }
        Role role1 = optionalRole.get();

        roleRepository.delete(role1);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
