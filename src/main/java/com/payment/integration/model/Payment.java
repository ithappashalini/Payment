package com.payment.integration.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.payment.integration.util.PaymentMode;
import com.payment.integration.util.PaymentStatus;

@Entity
@Table(name = "Payment")
public class Payment {
	@Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    @Column
    private String email;
    @Column
    private String payername;
    @Column
    private String phone;
    @Column
    private String productInfo;
    @Column
    private String amount;
    @Column
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    @Column
    @Temporal(TemporalType.DATE)
    private Date paymentDate;
    @Column
    private String txnId;
    @Column
    private String mihpayId;
    @Column
    @Enumerated(EnumType.STRING)
    private PaymentMode mode;
    @Column
    private String payeraddress;
    @Column
    @Temporal(TemporalType.DATE)
    private Date dueDate;
    @Column
    private String accountnumber;
    
    
	
	public String getPayername() {
		return payername;
	}
	public Long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
	public void setPayername(String payername) {
		this.payername = payername;
	}
	public String getPayeraddress() {
		return payeraddress;
	}
	public void setPayeraddress(String payeraddress) {
		this.payeraddress = payeraddress;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public String getAccountnumber() {
		return accountnumber;
	}
	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getProductInfo() {
		return productInfo;
	}
	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}
	
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	public String getMihpayId() {
		return mihpayId;
	}
	public void setMihpayId(String mihpayId) {
		this.mihpayId = mihpayId;
	}
	public PaymentMode getMode() {
		return mode;
	}
	public void setMode(PaymentMode mode) {
		this.mode = mode;
	}

    
    
    
}
