package com.example.project.ecommerce.controller;

import com.example.project.ecommerce.dto.OrderProductDTO;
import com.example.project.ecommerce.exception.ResourceNotFoundException;
import com.example.project.ecommerce.model.Order;
import com.example.project.ecommerce.model.OrderProduct;
import com.example.project.ecommerce.model.OrderStatus;
import com.example.project.ecommerce.service.OrderProductService;
import com.example.project.ecommerce.service.OrderService;
import com.example.project.ecommerce.service.ProductService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${order-base-path}")
public class OrderController {

    @Autowired
    ProductService productService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderProductService orderProductService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public @NotNull Iterable<Order> list() {
        return this.orderService.getAllOrders();
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody OrderForm form) {
        List<OrderProductDTO> formDTOs = form.getProductOrders();
        validateProductsExistence(formDTOs);
        Order order = new Order();
        order.setStatus(OrderStatus.PAID.name());
        order = this.orderService.create(order);

        List<OrderProduct> orderProducts = new ArrayList<>();
        for (OrderProductDTO dto : formDTOs) {
            orderProducts.add(orderProductService.create(new OrderProduct(order,
                    productService.getProduct(dto.getProduct().getId()), dto.getQuantity())));
        }

        order.setOrderProducts(orderProducts);

        this.orderService.update(order);

        String uri = ServletUriComponentsBuilder
          .fromCurrentServletMapping()
          .path("/orders/{id}")
          .buildAndExpand(order.getId())
          .toString();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri);

        return new ResponseEntity<>(order, headers, HttpStatus.CREATED);
    }

    private void validateProductsExistence(List<OrderProductDTO> orderProducts) {
        List<OrderProductDTO> nonExistingProductList = orderProducts
          .stream()
          .filter(op -> Objects.isNull(productService.getProduct(op.getProduct().getId())))
          .collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(nonExistingProductList)) {
            new ResourceNotFoundException("Product not found");
        }
    }

    @Getter @Setter
    public static class OrderForm {
        private List<OrderProductDTO> productOrders;
    }
}
