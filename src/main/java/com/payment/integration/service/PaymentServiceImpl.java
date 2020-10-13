package com.payment.integration.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.integration.model.PaymentCallback;
import com.payment.integration.model.PaymentDetail;
import com.payment.integration.repo.Payment;
import com.payment.integration.repo.PaymentRepo;
import com.payment.integration.util.PaymentStatus;
import com.payment.integration.util.PaymentUtil;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepo paymentRepository;

    @Override
    public boolean proceedPayment(PaymentDetail paymentDetail) {
        PaymentUtil paymentUtil = new PaymentUtil();
        //paymentDetail = paymentUtil.populatePaymentDetail(paymentDetail);
        
        Payment payment = new Payment();
        payment.setEmail(paymentDetail.getEmail());
        payment.setPayername(paymentDetail.getPayername());
        payment.setPhone(paymentDetail.getPhone());
        payment.setProductInfo(paymentDetail.getProductInfo());
        payment.setAmount(paymentDetail.getAmount());
        payment.setPaymentDate(new Date());
        payment.setTxnId(paymentDetail.getTxnId());
        payment.setMihpayId(paymentDetail.getMihpayId());
        payment.setPayeraddress(paymentDetail.getPayeraddress());
        payment.setDueDate(new Date());
        payment.setAccountnumber(paymentDetail.getAccountnumber());
        payment.setPaymentStatus(PaymentStatus.Pending);
        paymentRepository.save(payment);
        return true;
    }
    
    public List<Payment> getAllPayments(){
    	List<Payment> list = paymentRepository.findAll();
		return list;
    }

    @Override
    public String payuCallback(PaymentCallback paymentResponse) {
        String msg = "Transaction failed.";
        Payment payment = paymentRepository.findByTxnId(paymentResponse.getTxnid());
        if(payment != null) {
            //TODO validate the hash
            PaymentStatus paymentStatus = null;
            if(paymentResponse.getStatus().equals("failure")){
                paymentStatus = PaymentStatus.Failed;
            }else if(paymentResponse.getStatus().equals("success")) {
                paymentStatus = PaymentStatus.Success;
                msg = "Transaction success";
            }
            payment.setPaymentStatus(paymentStatus);
            payment.setMihpayId(paymentResponse.getMihpayid());
            payment.setMode(paymentResponse.getMode());
            paymentRepository.save(payment);
        }
        return msg;
    }

}
