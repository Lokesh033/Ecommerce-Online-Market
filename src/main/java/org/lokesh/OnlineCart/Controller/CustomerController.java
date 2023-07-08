package org.lokesh.OnlineCart.Controller;

import java.io.IOException;

import org.lokesh.OnlineCart.Dto.Customer;
import org.lokesh.OnlineCart.Dto.Merchant;
import org.lokesh.OnlineCart.Service.CustomerService;
import org.lokesh.OnlineCart.helper.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/login")
	public String gotoLogin() {
		return "CustomerLogin";
	}

	@GetMapping("/forgotpassword")
	public String gotoForgotoPassword() {
		return "CustomerForgotPassword";
	}

	@GetMapping("/signup")
	public String gotoSignup() {
		return "CustomerSignUp";
	}
	
	@PostMapping("/signup")
	public String signUp(ModelMap model,Customer customer, @RequestParam String date) {
		return customerService.signUp(model,customer,date) ;
		
	}
	@GetMapping("/verify-otp/{email}/{token}")
	public String verifyLink(@PathVariable String email,@PathVariable String token,ModelMap model) {
		return customerService.verifyLink(email,token,model);
	}
	
	@PostMapping("/login")
	public String login(Login login, HttpSession session,ModelMap model) {
		
		return customerService.login(login,session,model);
	}
	@PostMapping("/forgot-link")
	public String forgotLink(@RequestParam String email,ModelMap model){
		
		return customerService.forgotLink(email,model);
	}
	@GetMapping("/reset-password/{email}/{token}")
	public String resetPassword(@PathVariable String email,@PathVariable String token,ModelMap model) {
		return customerService.resetPassword(email,token,model);
	}
	
}
