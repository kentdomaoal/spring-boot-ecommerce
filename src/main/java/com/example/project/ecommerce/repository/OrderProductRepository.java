package com.example.project.ecommerce.repository;

import com.example.project.ecommerce.model.OrderProduct;
import com.example.project.ecommerce.model.OrderProductPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepository extends CrudRepository<OrderProduct, OrderProductPK> {
}
