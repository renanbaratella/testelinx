package com.linx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.linx.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
