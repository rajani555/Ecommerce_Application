package com.vgnit.shop.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import com.vgnit.shop.entity.User;
import com.vgnit.shop.repository.UserRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService
{
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		// TODO Auto-generated method stub
		User user = userRepository.findByEmail(username);
		if(user==null)
		{
			throw new UsernameNotFoundException(username);
		}
		return new CustomUserDetails(user);
	}

}
