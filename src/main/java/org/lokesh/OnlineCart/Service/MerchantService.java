package org.lokesh.OnlineCart.Service;

import java.io.IOException;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lokesh.OnlineCart.Dao.MerchantDao;
import org.lokesh.OnlineCart.Dto.Merchant;
import org.lokesh.OnlineCart.Dto.Product;
import org.lokesh.OnlineCart.helper.SendMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;

@Service
public class MerchantService {

	@Autowired
	MerchantDao merchantdao;
	
	
	@Autowired
	SendMail mail;
	
	public String signUp(ModelMap model, Merchant merchant, String date, MultipartFile pic) throws IOException {
		
		merchant.setDob(LocalDate.parse(date));
		
		byte[] picture=new byte[pic.getInputStream().available()];
		pic.getInputStream().read(picture);
		
		merchant.setPicture(picture);
		
		if(merchantdao.findByEmail(merchant.getEmail())!=null|| merchantdao.findByMobile(merchant.getMobile())!=null)
		{
			model.put("fail", "Email or Mobile should not be repeated");
			
			return "MerchantSignUp";
		}
		//logic for generating otp
		int otp=new Random().nextInt(100000,999999);
		merchant.setOtp(otp);
		
		// logic for Sending mail
	//	if(mail.sendOtp(merchant)) {
		
		Merchant merchant2=merchantdao.save(merchant);
		model.put("merchant", merchant2);
		return "SignupOtp";
	//	}	
//		else {
//			model.put("fail", "Somthing went Worng,Check email and try again");
//			return "MerchantSingup";
//		}
	
	}

	public String verifyOtp(String email, int otp,ModelMap model) {
		
		Merchant merchant=merchantdao.findByEmail(email);
		if(merchant.getOtp()==otp) {
			merchant.setStatus(true);
			merchant.setOtp(0);
			merchantdao.save(merchant);
			model.put("pass", "Account Verified Successfully");
			return "MerchantLogin";
		}
		else {
			model.put("fail", "Incorrect Otp");
			model.put("extra","again");
			model.put("merchant", merchant);
			return "SignupOtp";
		}
		
	}

	public String resendOtp(String email, ModelMap model) {
		
		Merchant merchant=merchantdao.findByEmail(email);
		
		//logic for generating otp
				int otp=new Random().nextInt(100000,999999);
				merchant.setOtp(otp);
				
				// logic for Sending mail
			//	if(mail.sendOtp(merchant)) {
				
				Merchant merchant2=merchantdao.save(merchant);
				model.put("merchant", merchant2);
				model.put("fail", "Otp resended");
				return "SignupOtp";
			//	}
			//	else {
			//		model.put("fail", "Somthing went Worng,Check email and try again");
			//		return "MerchantSingup";
			//	}
	}

	public String sendForgotOtp(String email, ModelMap model) {
		Merchant merchant=merchantdao.findByEmail(email);
		if(merchant==null) {
			model.put("fail","Email Doesn't Exist SignUp First");
			return "MerchantSignUp";
		}
		else {
			int otp=new Random().nextInt(100000,999999);
			merchant.setOtp(otp);
			
			// logic for Sending mail
		//	if(mail.sendOtp(merchant)) {
			
			Merchant merchant2=merchantdao.save(merchant);
			model.put("merchant", merchant2);
			model.put("pass", "otp send sucessfully");
			return "ForgotOtp";
		//	}
		//	else {
		//		model.put("fail", "Somthing went Worng,Check email and try again");
		//		return "MerchantForgotPassword";
		}
		
	}

	public String submitforgotOtp(String email, int otp, ModelMap model) {
		
		Merchant merchant=merchantdao.findByEmail(email);
		if(merchant.getOtp()==otp) {
			merchant.setStatus(true);
			merchant.setOtp(0);
			merchantdao.save(merchant);
			model.put("merchant", merchant);
			model.put("pass", "Account Verified Successfully");
			return "MerchantResetPassword";
		}
		else {
			model.put("fail", "Incorrect Otp");
			model.put("extra","again");
			model.put("merchant", merchant);
			return "ForgotOtp";
		}
	}

	public String resendForgotOtp(String email, ModelMap model) {
		
		Merchant merchant=merchantdao.findByEmail(email);
		
		//logic for generating otp
				int otp=new Random().nextInt(100000,999999);
				merchant.setOtp(otp);
				
				// logic for Sending mail
			//	if(mail.sendOtp(merchant)) {
				
				Merchant merchant2=merchantdao.save(merchant);
				model.put("merchant", merchant2);
				model.put("pass", "Otp resended");
				return "ForgotOtp";
			//	}
			//	else {
			//		model.put("fail", "Somthing went Worng,Check email and try again");
			//		return "MerchantForgotPassword";
			//	}
	}

	public String setPassword(String email, String password, ModelMap model) {
		
		Merchant merchant=merchantdao.findByEmail(email);
		merchant.setPassword(password);
		merchantdao.save(merchant);
		
		model.put("pass", "Password Reset Sucsess");
		return "MerchantLogin";
	}

	public String login(String email, String password, ModelMap model, HttpSession session) {
		
		Merchant merchant=merchantdao.findByEmail(email);
		
		if(merchant==null) {
			model.put("pass", "Incorrect Email");
			return "MechantLogin";
		}
		else {
			if(merchant.getPassword().equals(password)) {
				if(merchant.isStatus()) {
				session.setAttribute("merchant", merchant);
				model.put("pass","Login Sucsess");
				return "MerchantHome";
				}
				else {
					model.put("fail", "Mail verification Pending,click on Forgot pasword");
					return "MerchantLogin";
				}
			}else {
				model.put("fail", "Incorrect Password");
				return "MechantLogin";
			}
		}
	}

	public String addProduct(Product product, ModelMap model, MultipartFile pic, HttpSession session)
			throws IOException {
		Merchant merchant = (Merchant) session.getAttribute("merchant");

		byte[] image = new byte[pic.getInputStream().available()];
		pic.getInputStream().read(image);

		product.setImage(image);
		product.setName(merchant.getName() + "-" + product.getName());

		Product product2 = merchantdao.findProductByName(product.getName());
		if (product2 != null) {
			product.setId(product2.getId());
			product.setQuantity(product.getQuantity() + product2.getQuantity());
		}

		List<Product> products = merchant.getProducts();
		if (products == null) {
			products = new ArrayList<>();
		}
		products.add(product);
		merchant.setProducts(products);

		session.setAttribute("merchant", merchantdao.save(merchant));
		model.put("pass", "Product Added Successfully");
		return "MerchantHome";
	}

	public String fetchAllProducts(HttpSession session, ModelMap model) {
		Merchant merchant = (Merchant) session.getAttribute("merchant");

		if (merchant.getProducts() == null || merchant.getProducts().isEmpty()) {
			model.put("fail", "Products Not Found");
			return "MerchantHome";
		} else {
			model.put("products", merchant.getProducts());
			return "MerchantDisplayProduct";
		}
	}
	
	public String deleteProduct(HttpSession session, ModelMap model, int id) {
		Product product=merchantdao.findProductById(id);
		Merchant merchant=(Merchant) session.getAttribute("merchant");
		merchant.getProducts().remove(product);
		merchantdao.save(merchant);
		merchantdao.removeProduct(product);
		model.put("pass", "Deleted Successfully");
		if (merchant.getProducts() == null || merchant.getProducts().isEmpty()) {
			model.put("fail", "Products Not Found");
			return "MerchantHome";
		} else {
			model.put("products", merchant.getProducts());
			return "MerchantDisplayProduct";
		}
	}
	

}
