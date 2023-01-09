package com.example.project.ecommerce.stepdefinitions;

import com.example.project.ecommerce.CucumberContextConfig;
import com.example.project.ecommerce.model.Product;
import io.cucumber.java.en.*;
import org.assertj.core.api.Assertions;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ProductApiSteps extends CucumberContextConfig {

    private Iterable<Product> products;
    private Product product;

    @When("GET Products API was invoked to retrieve product list")
    public void get_products_api_was_invoked_to_retrieve_product_list() {
        ResponseEntity<Iterable<Product>> responseEntity =
                restTemplate.exchange(getBaseUrl(productBasePath+"/products"),
                        HttpMethod.GET, null, new ParameterizedTypeReference<Iterable<Product>>() {
        });
        products = responseEntity.getBody();
    }
    @Then("size of the product list should match")
    public void size_of_the_list_should_match(io.cucumber.datatable.DataTable dataTable) {
        should_match_the_size_of(products, dataTable);
    }
    @Then("the list should contain the products")
    public void the_list_should_contain_the_products(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> columns : rows) {
            assertThat(products, hasItem(hasProperty("name", is(columns.get("productName")))));
        }

    }

    @When("Product Id {} is passed in the API call to retrieve the product")
    public void product_id_is_passed_to_retrieve_the(Long productID) {
        ResponseEntity<Product> responseEntity =
                restTemplate.exchange(getBaseUrl(productBasePath+"/"+productID),
                        HttpMethod.GET, null, Product.class);

        product = responseEntity.getBody();
    }
    @Then("Product id {} and Name {} should match the following values")
    public void product_id_and_name_tv_set_should_match(Long productID, String productName) {
        assertThat(product.getId(), is(equalTo(productID)));
        assertThat(product.getName(), is(equalTo(productName)));
    }

    public static <T> void should_match_the_size_of(Iterable<T> actualObject, io.cucumber.datatable.DataTable dataTable){
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> columns : rows) {
            Assertions.assertThat(actualObject).hasSize(Integer.valueOf(columns.get("listSize")));
        }
    }

}
