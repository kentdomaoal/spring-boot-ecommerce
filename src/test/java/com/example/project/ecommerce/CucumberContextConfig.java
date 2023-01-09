package com.example.project.ecommerce;

import com.example.project.ecommerce.controller.OrderController;
import com.example.project.ecommerce.controller.ProductController;
import io.cucumber.java.Before;
import io.cucumber.spring.CucumberContextConfiguration;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Map;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CucumberContextConfig {

	@Autowired
	private ProductController productController;

	@Autowired
	private OrderController orderController;

	@LocalServerPort
	private int port;

	@Autowired
	protected TestRestTemplate restTemplate;

	@Value("${api-hostname}")
	private String apiHostname;
	@Value("${product-base-path}")
	protected String productBasePath;
	@Value("${order-base-path}")
	protected String orderBasePath;

	@Before
	void contextLoads() {
		Assertions.assertThat(productController).isNotNull();
		Assertions.assertThat(orderController).isNotNull();
	}

	protected String getBaseUrl(String endpoint){
		System.out.println("URL: "+ apiHostname + port + endpoint);
		return apiHostname + port + endpoint;
	}

}
