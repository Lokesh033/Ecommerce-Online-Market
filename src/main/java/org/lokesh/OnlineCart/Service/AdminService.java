package org.lokesh.OnlineCart.Service;

import java.util.List;

import org.lokesh.OnlineCart.Dto.Product;
import org.lokesh.OnlineCart.Repository.ProductRepository;
import org.lokesh.OnlineCart.helper.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;

@Service
public class AdminService {

	@Autowired
	ProductRepository productRepository;
	
	public String login(Login login, ModelMap model, HttpSession session) {
		model.put("name", "Admin");
		if(login.getEmail().equals("admin")){
			if(login.getPassword().equals("admin")) {
				session.setAttribute("admin", "admin");
				model.put("pass","Login Sucsuss");
				return "AdminHome";
			}
			else {
				model.put("fail","incorrect Password");
			}
		}
			else {
				model.put("fail", "Incorrect Email");
			}
		return "AdminHome";
		}

	public String fetchAllProducts(ModelMap model) {
		List<Product> list=productRepository.findAll();
		if(list.isEmpty())
		{
			model.put("fail", "No Products Found");
			return "AdminHome";
		}
		else {
			model.put("products", list);
			return "AdminDisplayProduct";
		}
	}
}
