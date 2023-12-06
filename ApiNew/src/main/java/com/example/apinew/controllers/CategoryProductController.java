package com.example.apinew.controllers;

import com.example.apinew.Models.CategoryProduct;
import com.example.apinew.repository.CategoryProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoryProductController {


    @Autowired
    private CategoryProductRepository categoryProductRepository;



    @GetMapping("/categoryProduct")
    public ResponseEntity<List<CategoryProduct>> getCategoryProduct(){
        List<CategoryProduct> categoryProducts = categoryProductRepository.findAll();

        return new ResponseEntity<>(categoryProducts, HttpStatus.OK);
    }

    @GetMapping("/categoryProduct/{idCategoryProduct}")
    public ResponseEntity<CategoryProduct> oneCategoryProduct(@PathVariable Long idCategoryProduct){
        Optional<CategoryProduct> optionalCategoryProduct = categoryProductRepository.findById(idCategoryProduct);

        if(optionalCategoryProduct.isPresent()){
            return new ResponseEntity<>(optionalCategoryProduct.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/categoryProduct")
    public ResponseEntity<CategoryProduct> createCategoryProduct(@Valid @RequestBody CategoryProduct categoryProduct){
        return new ResponseEntity<>(categoryProductRepository.save(categoryProduct), HttpStatus.CREATED);
    }

    @PutMapping("/categoryProduct/{idCategoryProduct}")
    public ResponseEntity<CategoryProduct> updateCategoryProduct(@PathVariable Long idCategoryProduct,
                                                                 @Valid @RequestBody CategoryProduct categoryProduct){
        Optional<CategoryProduct> optionalCategoryProduct = categoryProductRepository.findById(idCategoryProduct);

        if(optionalCategoryProduct.isEmpty()){
            return new ResponseEntity<>(optionalCategoryProduct.get(), HttpStatus.OK);
        }

        CategoryProduct categoryProduct1 = optionalCategoryProduct.get();
        categoryProduct1.setIdCategoryProduct(idCategoryProduct);
        categoryProduct1.setNameCategoryProduct(categoryProduct.getNameCategoryProduct());

        CategoryProduct categoryProductUpdate = categoryProductRepository.save(categoryProduct1);
        return new ResponseEntity<>(categoryProductUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/categoryProduct/{idCategoryProduct}")
    public ResponseEntity<?> deleteCategoryProduct(@PathVariable Long idCategoryProduct){
        Optional<CategoryProduct> optionalCategoryProduct = categoryProductRepository.findById(idCategoryProduct);

        if(optionalCategoryProduct.isEmpty()){
            return new ResponseEntity<>(optionalCategoryProduct.get(), HttpStatus.OK);
        }
        CategoryProduct categoryProduct1 = optionalCategoryProduct.get();

        categoryProductRepository.delete(categoryProduct1);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
