package com.memorynotfound.spring.security;import java.util.Arrays;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.memorynotfound.spring.security.model.Role;
import com.memorynotfound.spring.security.model.User;
import com.memorynotfound.spring.security.repository.UserRepository;

@Component
public class UserLoader implements ApplicationListener<ContextRefreshedEvent>{
	
	 private Logger log = Logger.getLogger(UserLoader.class);
	 
	 private UserRepository userRepository;
	    
	 
	  @Autowired
	    public void setUserRepository(UserRepository userRepository) {
	        this.userRepository = userRepository;
	    }
	 
	  @EventListener
	    public void onApplicationEvent(ContextRefreshedEvent event) {
		  
		  User user = new User();
		  user.setId(new Long(1));
	        user.setFirstName("kartikeya");
	        user.setLastName("gupta");
	        user.setEmail("kartikeya80@gmail.com");
	        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	        user.setPassword(encoder.encode("xdr56yhn"));
	        user.setRoles(Arrays.asList(new Role("ROLE_USER")));
	        userRepository.save(user);
		 
/*		  User kannu = new User("Kartikeya","Gupta","kartikeya80@gmail.com","xdr56yhn");
		  //User kannu = new User("Kartikeya","Gupta","kartikeya80@gmail.com",passwordEncoder.encode("xdr56yhn"));
		 kannu.setRoles(Arrays.asList(new Role("ROLE_USER")));
		 
		 userRepository.save(kannu);
		 */
		 
	 }
	 
	 

}
