package org.lokesh.OnlineCart.Controller;

import org.lokesh.OnlineCart.Service.AdminService;

import org.lokesh.OnlineCart.helper.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService adminservice;

	@GetMapping("/login")
	public String gotologin() {
		
		
		return "AdminLogin";
	}
	
	
	@PostMapping("/login")
	public String login( Login login,ModelMap model, HttpSession session) {
		
		return adminservice.login(login,model,session);
	}
	

	@GetMapping("/product-approve")
	public String viewAllProducts(HttpSession session,ModelMap model)
	{

		if(session.getAttribute("admin")==null)
		{
			model.put("fail", "Session Expied Login Again");
			return "AdminLogin";
		}
		else {
			return adminservice.fetchAllProducts(model);
		}
	}
		
		
}

