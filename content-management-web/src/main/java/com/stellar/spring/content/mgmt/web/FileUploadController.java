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
public class FileUploadController {

    private final StorageService storageService;
    
	@Autowired
	UserRepository UserRepositoryService;
	
	@Autowired
	PaymentDetailRepository paymentDetailRepositoryService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/upload")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
    	
        User user = (User) SecurityContextHolder.getContext() 
                .getAuthentication().getPrincipal();
        com.stellar.spring.content.mgmt.model.User user1 = UserRepositoryService.findByEmail(user.getUsername());
        
        List<PaymentDetail> paymentList = paymentDetailRepositoryService.findByUserIdAndFileName(user1.getId(), "http://localhost:8080/files/"+filename);
        
        if (paymentList.size()==0)
        	throw new RuntimeException("Please make payment");
        	
    	

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/uploadFile")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) {

        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }

    
    @PostMapping("/payFile")
    public String payFile(@RequestParam("file") String file,
            RedirectAttributes redirectAttributes) {
    	
        User user = (User) SecurityContextHolder.getContext() 
                .getAuthentication().getPrincipal();
        com.stellar.spring.content.mgmt.model.User user1 = UserRepositoryService.findByEmail(user.getUsername());
        
        PaymentDetail payment = new PaymentDetail();
        payment.setUserId(user1.getId());
        payment.setFileName(file);
        paymentDetailRepositoryService.save(payment);
    	
        /*storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
*/
        return "redirect:/upload";
    }
    
    
    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
