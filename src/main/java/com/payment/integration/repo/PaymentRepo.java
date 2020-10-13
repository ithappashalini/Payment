package com.payment.integration.repo;

import org.springframework.data.jpa.repository.JpaRepository;


public interface PaymentRepo extends JpaRepository<Payment, Long> {
    Payment findByTxnId(String txnId);
}

