package com.payment.integration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.payment.integration.model.PaymentDetail;
import com.payment.integration.model.Payment;
import com.payment.integration.model.PaymentCallback;
import com.payment.integration.service.PaymentService;
import com.payment.integration.util.PaymentMode;


@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api")
public class PaymentContoller {
	
	@Autowired
    private PaymentService paymentService;
	
	//MID 7263864
	
	@GetMapping("/users")
    public String getAllUsers() {
        return "hello payment";
    }
	
	@GetMapping
    public ModelAndView viewPaymentPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("paymentview");
        return model;
    }
	
	
	@PostMapping("/save-payment")
	public boolean saveStudent(@RequestBody PaymentDetail payment) {
		boolean  paymentDetails = paymentService.proceedPayment(payment);
		return paymentDetails;
	}
	
	@GetMapping("/payment-list")
	public List<Payment> getAllPayments() {
		 return paymentService.getAllPayments();
	}
	
    //@PostMapping(path = "/payment/pay")
//    @PostMapping(path = "/payment-details")
//    public @ResponseBody PaymentDetail proceedPayment(@RequestBody PaymentDetail paymentDetail){
//        return paymentService.proceedPayment(paymentDetail);
//    }

//    @RequestMapping(path = "/payment-response", method = RequestMethod.POST)
//    public @ResponseBody String payuCallback(@RequestParam String mihpayid, @RequestParam String status, @RequestParam PaymentMode mode, @RequestParam String txnid, @RequestParam String hash){
//        PaymentCallback paymentCallback = new PaymentCallback();
//        paymentCallback.setMihpayid(mihpayid);
//        paymentCallback.setTxnid(txnid);
//        paymentCallback.setMode(mode);
//        paymentCallback.setHash(hash);
//        paymentCallback.setStatus(status);
//        return paymentService.payuCallback(paymentCallback);
//    }
    
    @PostMapping("/update-status/{paymentId}")
	public List<Payment> updateStudent(@PathVariable("paymentId") long paymentId) {
		return paymentService.updatePaymentStatus(paymentId);
	}
    
    @GetMapping("/active-status")
    public List<Payment> getAllPaymentActiveStatus() {
		return paymentService.getAllPaymentActiveStatus();
	}
    
    @PostMapping("/update-payment-status/{paymentId}")
    public boolean removePaymentInCart(@PathVariable("paymentId") long paymentId) {
    	return paymentService.removePaymentInCart(paymentId);
    }
    
}
