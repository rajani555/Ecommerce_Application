package com.vgnit.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vgnit.shop.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> 
{
	// See Attention Here !!
	List<Address> findAllByuserId(Long id);
}
