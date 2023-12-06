package com.example.apinew.controllers;

import com.example.apinew.Models.Product;
import com.example.apinew.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;



    @GetMapping("/product")
    public ResponseEntity<List<Product>> getProduct(){
        List<Product> product = productRepository.findAll();

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/product/{idProduct}")
    public ResponseEntity<Product> oneProduct(@PathVariable Long idProduct){
        Optional<Product> optionalProduct = productRepository.findById(idProduct);

        if(optionalProduct.isPresent()){
            return new ResponseEntity<>(optionalProduct.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product){
        return new ResponseEntity<>(productRepository.save(product), HttpStatus.CREATED);
    }

    @PutMapping("/product/{idProduct}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long idProduct,
                                                   @Valid @RequestBody Product product){
        Optional<Product> optionalProduct = productRepository.findById(idProduct);

        if(optionalProduct.isEmpty()){
            return new ResponseEntity<>(optionalProduct.get(), HttpStatus.OK);
        }

        Product product1 = optionalProduct.get();
        product1.setIdProduct(idProduct);
        product1.setNameProduct(product.getNameProduct());
        product1.setPriceProduct(product.getPriceProduct());
        product1.setCountProduct(product.getCountProduct());
        product1.setStatusProduct(product.getStatusProduct());
        product1.setCategoryProduct(product.getCategoryProduct());
        product1.setProvider(product.getProvider());

        return new ResponseEntity<>(productRepository.save(product1), HttpStatus.OK);
    }

    @DeleteMapping("/product/{idProduct}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long idProduct){
        Optional<Product> optionalProduct = productRepository.findById(idProduct);

        if(optionalProduct.isEmpty()){
            return new ResponseEntity<>(optionalProduct.get(), HttpStatus.OK);
        }
        Product product1 = optionalProduct.get();

        productRepository.delete(product1);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
