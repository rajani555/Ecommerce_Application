package com.vgnit.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vgnit.shop.entity.CustomerOrder;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long>
{
	// See Attention Here !!
	List<CustomerOrder> findAllByuserId(Long id);
}
