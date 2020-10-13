package com.payment.integration.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.payment.integration.model.PaymentCallback;
import com.payment.integration.model.PaymentDetail;
import com.payment.integration.repo.Payment;

@Repository
public interface PaymentService {

	boolean proceedPayment(PaymentDetail paymentDetail);
	List<Payment> getAllPayments();
	String payuCallback(PaymentCallback paymentResponse);

}
