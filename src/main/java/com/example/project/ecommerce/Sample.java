package com.example.project.ecommerce;

import com.example.project.ecommerce.dto.OrderProductDTO;
import com.example.project.ecommerce.exception.ResourceNotFoundException;
import com.example.project.ecommerce.model.Product;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Sample {
    public static void main(String[] args) {
//        checkList();
//        printList();
        validateProduct();
    }

    public static void checkList() {
        List<String> list = new ArrayList<>();

        System.out.println(CollectionUtils.isEmpty(list));
        list = null;
        System.out.println(CollectionUtils.isEmpty(list));
//        System.out.println(list.size());

        if (CollectionUtils.isEmpty(list)) {
            System.out.println("Product not found");
            new ResourceNotFoundException("Product not found");
        }
    }

    public static void printList() {
        List<String> elements =
                Stream.of("a", "b", "c").filter(element -> element.contains("b"))
                        .collect(Collectors.toList());
        Optional<String> anyElement = elements.stream().findAny();
        Optional<String> firstElement = elements.stream().findFirst();

        for (String element : elements) {
            System.out.println(element);
        }
        System.out.println(anyElement.get());
        System.out.println(firstElement.get());

    }

    public static void validateProduct() {
        List<OrderProductDTO> orderProducts = new ArrayList<>();
        OrderProductDTO op1 = new OrderProductDTO();
        op1.setProduct(new Product(2L, "Game Console", 200.00, "http://placehold.it/200x100"));
        op1.setQuantity(5);
        orderProducts.add(op1);

        List<OrderProductDTO> nonExistingProductList = orderProducts
                .stream()
//                .filter(op -> Objects.isNull(op.getProduct().getId()))
                .filter(op -> Objects.isNull(null))
                .collect(Collectors.toList());

        System.out.println("nonExistingProductList is Empty: "+CollectionUtils.isEmpty(nonExistingProductList));

        if (!CollectionUtils.isEmpty(nonExistingProductList)) {
            System.out.println("Product not found");
            new ResourceNotFoundException("Product not found");
        }
    }
}