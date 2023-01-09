package com.example.project.ecommerce.dto;


import com.example.project.ecommerce.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderProductDTO {

    private Product product;
    private Integer quantity;

}
