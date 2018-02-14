package com.stellar.spring.content.mgmt.web;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.stellar.spring.content.mgmt.model.PaymentDetail;
import com.stellar.spring.content.mgmt.repository.PaymentDetailRepository;
import com.stellar.spring.content.mgmt.repository.UserRepository;
import com.stellar.spring.content.mgmt.storage.StorageFileNotFoundException;
import com.stellar.spring.content.mgmt.storage.StorageService;


@Controller
public class TransactionHistoryController {
	
	@Autowired
	UserRepository UserRepositoryService;
	

	@Autowired
	PaymentDetailRepository paymentDetailRepositoryService;


    @GetMapping("/transactions")
    public String listTransactions(Model model) throws IOException {
    	
        User user = (User) SecurityContextHolder.getContext() 
                .getAuthentication().getPrincipal();
        com.stellar.spring.content.mgmt.model.User user1 = UserRepositoryService.findByEmail(user.getUsername());
    	
        List<PaymentDetail> paymentList = paymentDetailRepositoryService.findByUserId(user1.getId());
        
       
        
        model.addAttribute("paymentList",  paymentList);

        return "transactions";
    }

}
