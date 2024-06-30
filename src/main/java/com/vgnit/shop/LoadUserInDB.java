package com.vgnit.shop;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.vgnit.shop.entity.User;
import com.vgnit.shop.repository.UserRepository;
import com.vgnit.shop.service.UserService;

@Component
public class LoadUserInDB implements CommandLineRunner 
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception 
	{
		// TODO Auto-generated method stub
		
		User user= new User();
		
		user.setFullname("ADMIN");
		user.setGender("Male");
		user.setMobile("1234567890");
		user.setEmail("admin@gmail.com");
		user.setPassword("$2a$10$QB3R6aIPn9K97h1glpS8cufv47FewrxNSDZr5hs.eNPTOIOd5sxQi");  // i.e. 123456
		user.setRole("ROLE_ADMIN");
		user.setCreatedAt(LocalDate.now());
		
		boolean checkEmail = userService.existEmail(user.getEmail());
		if (checkEmail) 
		{
			//System.out.println("This email is already exist");
		} 
		else 
		{
			userRepository.save(user);
		}
	}


}
