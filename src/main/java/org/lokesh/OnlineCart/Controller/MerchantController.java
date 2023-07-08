package org.lokesh.OnlineCart.Controller;

import java.io.IOException;

import org.lokesh.OnlineCart.Dto.Merchant;
import org.lokesh.OnlineCart.Dto.Product;
import org.lokesh.OnlineCart.Service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.mail.Multipart;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/merchant")
public class MerchantController {

	@Autowired
	MerchantService merchantservice;
	
	@GetMapping("/login")
	public String gotologin() {
		
		
		return "MerchantLogin";
	}
	
	@GetMapping("/forgotpassword")
	public String gotoForgotPassword() {
		return "MerchantForgotPassword";
	}
	
	@GetMapping("/signup")
	public String gotoSignUp() {
		return "MerchantSignUp";
	}
	
	@PostMapping("/signup")
	public String signUp(ModelMap model, Merchant merchant,@RequestParam String date,@RequestParam MultipartFile pic) throws IOException {
		return merchantservice.signUp(model,merchant,date,pic) ;
		
	}
	@PostMapping("/forgotpassword")
	public String sendForgotOtp(@RequestParam String email, ModelMap model) {
		return merchantservice.sendForgotOtp(email, model);
	}
	
	@PostMapping("/verify-otp/{email}")
	public String verifyOtp(@PathVariable String email,@RequestParam int otp,ModelMap model) {
		
		return merchantservice.verifyOtp(email,otp,model);
		
	}
	@GetMapping("/resend-otp/{email}")
	public String resendOtp(@PathVariable String email,ModelMap model) {
		return merchantservice.resendOtp(email,model);
	}
	
	@PostMapping("/forgot-otp/{email}")
	public String submitforgotOtp(@PathVariable String email,@RequestParam int otp,ModelMap model) {
	
		return merchantservice.submitforgotOtp(email,otp,model);
	}
	
	@GetMapping("/resend-forgot-otp/{email}")
	public String resendForgotOtp(@PathVariable String email,ModelMap model) {
		return merchantservice.resendForgotOtp(email,model);
	}
	@PostMapping("/resetpassword")
	public String setPassword(@RequestParam String email,@RequestParam String password,ModelMap model) {
	
		return merchantservice.setPassword(email,password,model);
	}
	@PostMapping("/login")
	public String login(@RequestParam String email, @RequestParam String password,ModelMap model,HttpSession session) {
		
		return merchantservice.login(email,password,model,session);
	}
	
	@GetMapping("/product-add")
	public String gotoAddProductPage(HttpSession session,ModelMap model)
	{
		if(session.getAttribute("merchant")==null)
		{
			model.put("fail", "Session Expied Login Again");
			return "MerchantLogin";
		}
		else {
			model.put("merchant", session.getAttribute("merchant"));
			return "AddProduct";
		}
	}

	
	@PostMapping("/product-add")
	public String addProduct(HttpSession session,ModelMap model,Product product,@RequestParam MultipartFile pic) throws IOException
	{
		if(session.getAttribute("merchant")==null)
		{
			model.put("fail", "Session Expied Login Again");
			return "MerchantLogin";
		}
		else {
			return merchantservice.addProduct(product,model,pic,session);
		}
	}
	
	@GetMapping("/product-view")
	public String fetchAllProducts(HttpSession session,ModelMap model)
	{
		if(session.getAttribute("merchant")==null)
		{
			model.put("fail", "Session Expied Login Again");
			return "MerchantLogin";
		}
		else {
			return merchantservice.fetchAllProducts(session,model);
		}
	}
	
	@GetMapping("/product-delete/{id}")
	public String fetchAllProducts(@PathVariable int id,HttpSession session,ModelMap model)
	{
		if(session.getAttribute("merchant")==null)
		{
			model.put("fail", "Session Expied Login Again");
			return "MerchantLogin";
		}
		else {
			return merchantservice.deleteProduct(session,model,id);
		}
	}
	
	
}
