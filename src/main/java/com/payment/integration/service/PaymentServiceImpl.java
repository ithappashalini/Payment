package com.payment.integration.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.integration.model.PaymentCallback;
import com.payment.integration.model.PaymentDetail;
import com.payment.integration.model.Payment;
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

//    @Override
//    public String payuCallback(PaymentCallback paymentResponse) {
//        String msg = "Transaction failed.";
//        Payment payment = paymentRepository.findByTxnId(paymentResponse.getTxnid());
//        if(payment != null) {
//            //TODO validate the hash
//            PaymentStatus paymentStatus = null;
//            if(paymentResponse.getStatus().equals("failure")){
//                paymentStatus = PaymentStatus.Failed;
//            }else if(paymentResponse.getStatus().equals("success")) {
//                paymentStatus = PaymentStatus.Success;
//                msg = "Transaction success";
//            }
//            payment.setPaymentStatus(paymentStatus);
//            payment.setMihpayId(paymentResponse.getMihpayid());
//            payment.setMode(paymentResponse.getMode());
//            paymentRepository.save(payment);
//        }
//        return msg;
//    }
    
    public Optional<Payment> getPayerDetails(Long payment_id) {
    	Optional<Payment> payment = paymentRepository.findById(payment_id);
    	return payment;
    }
    
    public List<Payment> updatePaymentStatus(long payment_id){
    	Payment payment = new Payment();
    	Optional<Payment> paymentlist =Optional.ofNullable(new  Payment());
    	boolean status=false;
		List<Payment> activeList = new ArrayList<Payment>();
		long id = payment_id;
		try {
			if(id == 0) {
				activeList = paymentRepository.findAllByPaymentStatus(payment.getPaymentStatus());
			}
			paymentlist = getPayerDetails(payment_id);
			payment.setPaymentId(paymentlist.get().getPaymentId());
			payment.setAmount(paymentlist.get().getAmount());
			payment.setEmail(paymentlist.get().getEmail());
	        payment.setPayername(paymentlist.get().getPayername());
	        payment.setPhone(paymentlist.get().getPhone());
	        payment.setProductInfo(paymentlist.get().getProductInfo());
	        payment.setPaymentDate(new Date());
	        payment.setTxnId(paymentlist.get().getTxnId());
	        payment.setMihpayId(paymentlist.get().getMihpayId());
	        payment.setPayeraddress(paymentlist.get().getPayeraddress());
	        payment.setDueDate(new Date());
	        payment.setAccountnumber(paymentlist.get().getAccountnumber());
	        payment.setPaymentStatus(paymentlist.get().getPaymentStatus());
			status=true;
			if(status = true) {
				if(!payment.getPaymentStatus().equals(PaymentStatus.Active)) {
					payment.setPaymentStatus(PaymentStatus.Active);
					payment = paymentRepository.save(payment);
				}
               activeList = paymentRepository.findAllByPaymentStatus(payment.getPaymentStatus());
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return activeList;
    }
    
    public List<Payment> getAllPaymentActiveStatus(){
    	Payment payment = new Payment();
    	payment.setPaymentStatus(PaymentStatus.Active);
    	List<Payment> activeList = new ArrayList<Payment>();
    	activeList = paymentRepository.findAllByPaymentStatus(payment.getPaymentStatus());
    	return activeList;
    }
    
    
    public boolean removePaymentInCart(long payment_id){
    	Optional<Payment> paymentlist =Optional.ofNullable(new  Payment());
    	paymentlist = getPayerDetails(payment_id);
    	Payment payment = new Payment();
		payment.setPaymentId(paymentlist.get().getPaymentId());
		payment.setAmount(paymentlist.get().getAmount());
		payment.setEmail(paymentlist.get().getEmail());
        payment.setPayername(paymentlist.get().getPayername());
        payment.setPhone(paymentlist.get().getPhone());
        payment.setProductInfo(paymentlist.get().getProductInfo());
        payment.setPaymentDate(new Date());
        payment.setTxnId(paymentlist.get().getTxnId());
        payment.setMihpayId(paymentlist.get().getMihpayId());
        payment.setPayeraddress(paymentlist.get().getPayeraddress());
        payment.setDueDate(new Date());
        payment.setAccountnumber(paymentlist.get().getAccountnumber());
        payment.setPaymentStatus(PaymentStatus.Pending);
        payment = paymentRepository.save(payment);
        return true;
    }

}
