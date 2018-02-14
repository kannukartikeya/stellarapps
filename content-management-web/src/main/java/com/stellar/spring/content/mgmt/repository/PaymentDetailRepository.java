package com.stellar.spring.content.mgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stellar.spring.content.mgmt.model.PaymentDetail;
import com.stellar.spring.content.mgmt.model.User;

@Repository
public interface PaymentDetailRepository extends JpaRepository<PaymentDetail, Long> {

	List<PaymentDetail> findByUserIdAndFileName(Long userId,String fileName);
	
	List<PaymentDetail> findByUserId(Long userId);
	

}
