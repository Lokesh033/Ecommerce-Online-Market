package org.lokesh.OnlineCart.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.servlet.http.HttpSession;


@Controller
public class MainController {

//	@GetMapping("/")
//	public String firstpage() {
//		return  "redirect:/home";
//	}
	
	
	@GetMapping("/")
	public String gotohome() {
		
		return "Home";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session,ModelMap model) {
		session.invalidate();
		model.put("fail", "Logout Success");
		return "Home";
	}
}
