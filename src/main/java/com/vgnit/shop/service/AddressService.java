package com.vgnit.shop.service;

import java.security.Principal;
import java.util.List;
import com.vgnit.shop.dto.AddressDTO;
import com.vgnit.shop.entity.Address;
public interface AddressService 
{
	public Address saveAddress(AddressDTO addressDTO, Principal principal);
	
	public List<Address> listOfAddress();

	public String deleteAddress(Long id);
	
	public Address getOrEditAddressById(Long id);
	
	public List<Address> findAllAddressByuserId(Long id);
	
}
