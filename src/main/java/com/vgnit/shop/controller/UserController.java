package com.vgnit.shop.controller;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vgnit.shop.dto.UserDto;
import com.vgnit.shop.entity.User;
import com.vgnit.shop.service.CategoryService;
import com.vgnit.shop.service.UserService;
import ForAll.UniversalData;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController 
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;
	
	// Specifically for "USER" registration page open...!
	@GetMapping("/registration")
	public String openSignUp(Model model)
	{
		UserDto userDto= new UserDto();
		model.addAttribute("user", userDto);
		model.addAttribute("listofCategoryWithOutKey", categoryService.listofCategory());
		model.addAttribute("cartCountValue", UniversalData.cart.size());
		return "sign-in";
	}
	
	// Specifically for "ADMIN" registration page open...!
	@GetMapping("/admin/registration")
	public String openSignUpAdmin(Model model)
	{
		UserDto userDto= new UserDto();
		model.addAttribute("user", userDto);
		model.addAttribute("listofCategoryWithOutKey", categoryService.listofCategory());
		model.addAttribute("cartCountValue", UniversalData.cart.size());
		return "sign-in-admin";
	}
	
	// Specifically for "USER" registration (POST) page...!
	@PostMapping("/registration")
	public String createUser(@ModelAttribute("user") UserDto userDto, HttpSession session)
	{
		User email = userService.checkEmail(userDto.getEmail());
		if(email!=null)
		{
			session.setAttribute("msg_wrg", "This user already exists! Try logging in.");
		}
		else
		{
			userService.createUser(userDto);
			session.setAttribute("msg_rig", "User registerd successfully...Log in now!");
		}
		
		return "redirect:/registration";
	}
	
	// Specifically for "ADMIN" registration (POST) page...!
	@PostMapping("/admin/registration")
	public String createAdmin(@ModelAttribute("user") UserDto userDto, HttpSession session)
	{
		User email = userService.checkEmail(userDto.getEmail());
		if(email!=null)
		{
			session.setAttribute("msg_wrg", "This user already exists! Try logging in.");
		}
		else
		{
			userService.createUser(userDto);
			session.setAttribute("msg_rig", "User registerd successfully...Log in now!");
		}
		
		return "redirect:/admin/registration";
	}
	

	@ModelAttribute
	public void getLoggedUser(Model model)
	{
		String Flashmessage = "message";
		
// 		FLASH MESSAGE WITH TOASTR MODE !!
		model.addAttribute("message", Flashmessage);
	}
		
	// Login !!
	@GetMapping("/login")
	public String login(Model model, Principal principal, @ModelAttribute("message") String Flashmessage, RedirectAttributes redirectAttributes) 
	{	
		if(principal!=null)
		{
			String email = principal.getName();
			User user = userService.checkEmail(email);
			
			model.addAttribute("user", user);
			
			String role = user.getRole().toUpperCase();
			if(role.equalsIgnoreCase("ROLE_ADMIN"))
			{
				model.addAttribute("admin", role);
				model.addAttribute("user", user);
				
				model.addAttribute("listofCategoryWithOutKey", categoryService.listofCategory());
				model.addAttribute("cartCountValue", UniversalData.cart.size());   //Attention Here !!
				
				return "login-admin";
			}
			else
			{
				model.addAttribute("user", user);
				
			}
		}
		
		model.addAttribute("listofCategoryWithOutKey", categoryService.listofCategory());
		model.addAttribute("cartCountValue", UniversalData.cart.size());   //Attention Here !!
		
//		FLASH MESSAGE WITH TOASTR MODE !!
		redirectAttributes.addFlashAttribute("message", "If item added");
		
// 		FLASH MESSAGE WITH TOASTR MODE !!
		model.addAttribute("message", Flashmessage);
		
		return "login";
	}
	
	// View my profile
	@GetMapping("/ViewMyProfile")
	public String viewProfile(Model model, Principal principal)
	{
		if(principal!=null)
		{
			String email = principal.getName();
			User user = userService.checkEmail(email);
			
			model.addAttribute("user", user);
		}
		
		return "view-profile";
	}
	
	// Sample exception handling customization
	@GetMapping("/errors/access-denied")
	public String accessDenied()
	{
		return "ERROR-403";
	}
	
	// Taken this two methods command over Users...!
	@GetMapping("/admin/listOfUser")
	public String listOfUsers(Model model)
	{
		List<User> listOfUsers = userService.listOfUsers();
		// JAVA 8 !!
		List<User> collect = listOfUsers.stream().filter(user -> user.getRole().equalsIgnoreCase("ROLE_USER")).collect(Collectors.toList());
		model.addAttribute("listOfUsers", collect);
		
		List<User> collect2 = listOfUsers.stream().filter(admin -> admin.getRole().equalsIgnoreCase("ROLE_ADMIN")).collect(Collectors.toList());
		model.addAttribute("listOfAdmins", collect2);
		return "user-list";
	}
	
	@GetMapping("/admin/deleteUser/{id}")
	public String deleteUser(@PathVariable(value="id") Long id)
	{
		userService.deleteUser(id);
		return "redirect:/admin/listOfUser";
	}
	
	// Taken this two methods command over Admin.s...!
	@GetMapping("/admin/listOfAdmins")
	public String listOfAdmins(Model model)
	{
		List<User> listOfAdmins = userService.listOfUsers();
		// JAVA 8 !!
		List<User> collect2 = listOfAdmins.stream().filter(admin -> admin.getRole().equalsIgnoreCase("ROLE_ADMIN")).collect(Collectors.toList());
		model.addAttribute("listOfAdmins", collect2);
		return "admin-list";
	}
	
	@GetMapping("/admin/deleteAdmin/{id}")
	public String deleteAdmin(@PathVariable(value="id") Long id)
	{
		userService.deleteUser(id);
		return "redirect:/admin/listOfAdmins";
	}
	
}
