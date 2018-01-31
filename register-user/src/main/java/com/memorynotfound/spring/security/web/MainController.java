package com.memorynotfound.spring.security.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @GetMapping("/")
    public ModelAndView root() {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
            Map<String, Object> userModel = new HashMap<String, Object>();
            userModel.put("username", user.getUsername());
            return new ModelAndView("index", "model", userModel);    	
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
