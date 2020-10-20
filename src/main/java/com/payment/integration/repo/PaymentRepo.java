package com.payment.integration.repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.payment.integration.model.Payment;
import com.payment.integration.util.PaymentStatus;;

public interface PaymentRepo extends JpaRepository<Payment, Long> {

	List<Payment> findAllByPaymentStatus(PaymentStatus paymentStatus);
    
}

