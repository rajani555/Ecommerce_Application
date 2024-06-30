package com.vgnit.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vgnit.shop.entity.User;

public interface UserRepository extends JpaRepository<User, Long>
{
	public User findByEmail(String email);
	
	public boolean existsByEmail(String email);
	
}
