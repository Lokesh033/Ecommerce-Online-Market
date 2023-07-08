package org.lokesh.OnlineCart.Service;

import java.time.LocalDate;
import java.util.Random;

import org.lokesh.OnlineCart.Dao.CustomerDao;
import org.lokesh.OnlineCart.Dto.Customer;
import org.lokesh.OnlineCart.Dto.Customer;
import org.lokesh.OnlineCart.Dto.Customer;
import org.lokesh.OnlineCart.Dto.Customer;
import org.lokesh.OnlineCart.helper.Login;
import org.lokesh.OnlineCart.helper.SendMail;
import org.lokesh.OnlineCart.Dto.Customer;
import org.lokesh.OnlineCart.Dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import jakarta.servlet.http.HttpSession;

@Service
public class CustomerService {
	
	@Autowired
	CustomerDao customerDao;


	@Autowired
	SendMail mail;
	
	public String signUp(ModelMap model, Customer customer, String date) {
		
		customer.setDob(LocalDate.parse(date));
		if(customerDao.findByEmail(customer.getEmail())!=null|| customerDao.findByMobile(customer.getMobile())!=null)
		{
			model.put("fail", "Email or Mobile should not be repeated");
			
			return "CustomerSignUp";
		}
		String token="ekart"+new Random().nextInt(10000,999999);
		customer.setToken(token);
	
		if(mail.sendLink(customer)) {
			
			customerDao.save(customer);
			model.put("pass", "Verification Link is sent to Email click on that link");
			return "CustomerLogin";
			}	
			else {
				model.put("fail", "Somthing went Worng,Check email and try again");
				return "CustomerSingup";
			}
	}

	public String verifyLink(String email, String token, ModelMap model) {
		Customer customer=customerDao.findByEmail(email);
		if(customer.getToken()==token) {
			customer.setStatus(true);
			customer.setToken(null);
			customerDao.save(customer);
			model.put("pass", "Account Verified Successfully");
			return "CustomerLogin";
		}
		else {

			model.put("fail", "Incorrect Link");
			return "CustomerLogin";
		}
	}

	public String login(Login login, HttpSession session, ModelMap model) {
     
		Customer customer=customerDao.findByEmail(login.getEmail());
		
		if(customer==null) {
			model.put("pass", "Incorrect Email");
			return "MechantLogin";
		}
		else {
			if(customer.getPassword().equals(login.getPassword())) {
				if(customer.isStatus()) {
				session.setAttribute("customer", customer);
				model.put("pass","Login Sucsess");
				return "CustomerHome";
				}
				else {
					model.put("fail", "Mail verification Pending,click on Forgot pasword");
					return "CustomerLogin";
				}
			}else {
				model.put("fail", "Incorrect Password");
				return "CustomerLogin";
			}
		}
	}

	public String forgotLink(String email, ModelMap model) {
		
		Customer customer=customerDao.findByEmail(email);
		
		if(customer==null) {
			model.put("pass", "Incorrect Email");
			return "CustomerForgotPassword";
		}
		else {
			String token="ekart"+new Random().nextInt(10000,999999);
			customer.setToken(token);
		
			if(mail.sendResetLink(customer)) {
				
				customerDao.save(customer);
				model.put("pass", "Verification Link is sent to Email click on that link");
				return "CustomerLogin";
				}	
				else {
					model.put("fail", "Somthing went Worng,Check email and try again");
					return "CustomerSingup";
				}
		}
		
	}

	 public String resetPassword(String email, String token, ModelMap model) {
		
		 Customer customer=customerDao.findByEmail(email);
		 if(customer.getToken().equals(token)) {
			 customer.setStatus(true);
			 customer.setToken(null);
			 model.put("customer", customerDao.save(customer));
			 return "CustomerResetPassword";
		 }
		 else {
			 model.put("fail", "somthing went wrong");
			 return "CustomerLogin";
		 }
	}

	public String setPassword(String email, String password, ModelMap model) {


		Customer customer=customerDao.findByEmail(email);
		customer.setPassword(password);
		customerDao.save(customer);
		
		model.put("pass", "Password Reset Sucsess");
		return "CustomerLogin";
	}

}
