package com.example.project.ecommerce.controller;

import com.example.project.ecommerce.model.Product;
import com.example.project.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("${product-base-path}")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/products" )
    public @NotNull Iterable<Product> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping(value = "/{id}" )
    public Product getProductByID(@PathVariable("id") long id) {
        return productService.getProduct(id);
    }
}
