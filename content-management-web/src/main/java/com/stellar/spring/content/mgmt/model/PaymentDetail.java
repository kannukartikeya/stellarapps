package com.stellar.spring.content.mgmt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PaymentDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 

	private String fileName;
    private Long userId;

    public PaymentDetail() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getFileName() {
 		return fileName;
 	}


 	public void setFileName(String fileName) {
 		this.fileName = fileName;
 	}


 	public Long getUserId() {
 		return userId;
 	}


 	public void setUserId(Long userId) {
 		this.userId = userId;
 	}

}
