package com.stellar.spring.content.mgmt.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.stellar.spring.content.mgmt.repository.UserRepository;
import com.stellar.spring.content.mgmt.service.StellarAccountService;

@Controller
public class MainController {
	
	@Autowired
	UserRepository UserRepositoryService;
	
	@Autowired
	StellarAccountService stellarAccountServiceImpl;

    @GetMapping("/")
    public String root(Model model) {
        User user = (User) SecurityContextHolder.getContext() 
                .getAuthentication().getPrincipal();
        com.stellar.spring.content.mgmt.model.User user1 = UserRepositoryService.findByEmail(user.getUsername());
        user1.setAccountBalance(stellarAccountServiceImpl.getAccountBalance(user1));
        
        	model.addAttribute("user", user1);
            return "index" ;    	
        //return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }
}
