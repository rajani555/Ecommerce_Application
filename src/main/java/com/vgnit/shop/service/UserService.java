package com.vgnit.shop.service;

import java.util.List;

import com.vgnit.shop.dto.UserDto;
import com.vgnit.shop.entity.User;

public interface UserService 
{
	User createUser(UserDto userDto);
	
	User checkEmail(String email);
	
	User getOrEditUserById(Long id);
	
	boolean existEmail(String email);

	List<User> listOfUsers();

	void deleteUser(Long id);
}
