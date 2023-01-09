package com.example.project.ecommerce.stepdefinitions;


import com.example.project.ecommerce.CucumberContextConfig;
import com.example.project.ecommerce.controller.OrderController;
import com.example.project.ecommerce.dto.OrderProductDTO;
import com.example.project.ecommerce.model.Order;
import com.example.project.ecommerce.model.Product;
import io.cucumber.java.en.*;
import org.assertj.core.api.Assertions;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static com.example.project.ecommerce.stepdefinitions.ProductApiSteps.*;

public class OrderApiSteps extends CucumberContextConfig {

    private Iterable<Order> orders;
    private Order order;
    private ResponseEntity<Order> postResponse;
    private OrderController.OrderForm orderForm;

    @Given("There are no orders yet")
    public void there_are_no_orders_yet() {
    }
    @When("GET Orders API was invoked to retrieve the order list")
    public void get_orders_api_was_invoked_to_retrieve_the_order_list() {
        ResponseEntity<Iterable<Order>> responseEntity =
                restTemplate.exchange(getBaseUrl(orderBasePath),
                        HttpMethod.GET, null, new ParameterizedTypeReference<Iterable<Order>>() {
                        });
        orders = responseEntity.getBody();
    }
    @Then("size of the order list should match")
    public void size_of_the_list_should_match(io.cucumber.datatable.DataTable dataTable) {
        should_match_the_size_of(orders, dataTable);
    }
    @Given("Order form was prepared")
    public void order_form_was_prepared() {
        orderForm = new OrderController.OrderForm();
        OrderProductDTO productDTO = new OrderProductDTO();
        productDTO.setProduct(new Product(1L, "TV Set", 300.00, "http://placehold.it/200x100"));
        productDTO.setQuantity(2);
        orderForm.setProductOrders(Collections.singletonList(productDTO));
    }
    @When("POST Orders API was invoked")
    public void post_orders_api_was_invoked() {
        postResponse = restTemplate.postForEntity(getBaseUrl(orderBasePath), orderForm, Order.class);
        order = postResponse.getBody();
    }
    @Then("response status code should be equal to CREATED")
    public void response_status_code_should_be_equal_to_created() {
        Assertions.assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
    @Then("response should contain the Order with status {}")
    public void response_should_contain_the_order_with_status_status(String status) {
        assertThat(order, hasProperty("status", is(status)));
    }
    @Then("response should contain the Product Order with quantity equals to {}")
    public void response_should_contain_the_product_order_with_quantity_equals_to_quantity(int quantity) {
        assertThat(order.getOrderProducts(), hasItem(hasProperty("quantity", is(quantity))));
    }

}
