package com.vgnit.shop.service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.vgnit.shop.dto.UserDto;
import com.vgnit.shop.entity.User;
import com.vgnit.shop.repository.UserRepository;
import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User createUser(UserDto userDto) 
	{
		// TODO Auto-generated method stub
		User user = new User();
		user.setFullname(userDto.getFullname());
		user.setGender(userDto.getGender());
		user.setMobile(userDto.getMobile());
		user.setEmail(userDto.getEmail());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		
//		user.setRole(userDto.getRole());
//		user.setRole("ROLE_USER");
		
		if(userDto.getRole()==null)
		{
			user.setRole("ROLE_USER");
		}
		else
		{
			user.setRole(userDto.getRole());
		}
		
		user.setCreatedAt(LocalDate.now());
		return userRepository.save(user);
	}

	@Override
	public User checkEmail(String email) 
	{
		// TODO Auto-generated method stub
		User user = userRepository.findByEmail(email);
		return user;
	}
	
	// (*)
	public void revSessMsgWrg()
	{
		HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest().getSession();
		session.removeAttribute("msg_wrg");
	}
	
	// (*)
	public void revSessMsgRig()
	{
		HttpSession session= ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest().getSession();
		session.removeAttribute("msg_rig");
	}

	@Override
	public User getOrEditUserById(Long id) 
	{
		// TODO Auto-generated method stub
		Optional<User> findById = userRepository.findById(id);
		User user = findById.get();
		return user;
	}

	@Override
	public boolean existEmail(String email) 
	{
		// TODO Auto-generated method stub
		boolean existsByEmail = userRepository.existsByEmail(email);
		return existsByEmail;
	}

	@Override
	public List<User> listOfUsers() 
	{
		// TODO Auto-generated method stub
		List<User> findAll = userRepository.findAll();
		return findAll;
	}

	@Override
	public void deleteUser(Long id) 
	{
		// TODO Auto-generated method stub
		userRepository.deleteById(id);
	}

}
