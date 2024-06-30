package com.vgnit.shop.service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vgnit.shop.dto.AddressDTO;
import com.vgnit.shop.entity.Address;
import com.vgnit.shop.entity.User;
import com.vgnit.shop.repository.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService 
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private AddressRepository addressRepository;

	@Override
	public Address saveAddress(AddressDTO addressDTO, Principal principal)
	{
		// TODO Auto-generated method stub
		Address address = new Address();
		
		address.setId(addressDTO.getId());
		address.setFirstName(addressDTO.getFirstName());
		address.setLastName(addressDTO.getLastName());
		address.setStreetAddress(addressDTO.getStreetAddress());
		address.setCity(addressDTO.getCity());
		address.setState(addressDTO.getState());
		address.setZipCode(addressDTO.getZipCode());
		
	/*	// Trick: 1
		User user = new User();
		User userById = userService.getOrEditUserById(user.getId());
		address.setUser(userById);	*/
		
		// Trick: 2
		if(principal!=null)
		{
			String email = principal.getName();
			User userFound = userService.checkEmail(email);
			address.setUser(userService.getOrEditUserById(userFound.getId()));
		}
		
		address.setMobile(addressDTO.getMobile());
		address.setCreatedAt(LocalDate.now());
		
		Address save = addressRepository.save(address);
		return save;
	}

	@Override
	public List<Address> listOfAddress() 
	{
		// TODO Auto-generated method stub
		List<Address> listOfAddress = addressRepository.findAll();
		return listOfAddress;
	}

	@Override
	public String deleteAddress(Long id) 
	{
		// TODO Auto-generated method stub
		Address address = addressRepository.findById(id).get();
		if(address!=null)
		{
			addressRepository.delete(address);
			return "Address deleted successfully";
		}
		else 
		{
			return "Something went on server...!";
		}
		
	}

	@Override
	public Address getOrEditAddressById(Long id) 
	{
		// TODO Auto-generated method stub
		Optional<Address> findById = addressRepository.findById(id);
		Address address = findById.get();
		return address;
	}

	@Override
	public List<Address> findAllAddressByuserId(Long id) 
	{
		// TODO Auto-generated method stub
		List<Address> findAllAddressByuserId = addressRepository.findAllByuserId(id);
		return findAllAddressByuserId;
	}

}
