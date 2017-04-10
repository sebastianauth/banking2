package com.emma.finance.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("GIRO")
public class GiroTransaction extends FinancialTransaction {
	
	//Buchungstext
	@Column(name="transaction_type")
	private String transactionType;
	
	//Auftraggeber/Beguenstigter
	@Column(name="recipient_initiator")
	private String recipientInitiator;
	
	
	//IBAN, Kontonummer
	@Column(name="account_number")
	private String accountNumber;
	
	@Column(name="bank_code")
	private String bankCode;
	
	//Gläubiger-ID
	@Column(name="creditor_id")
	private String creditorId;
	
	//Mandatsreferenz
	@Column(name="mandate")
	private String mandate;
	
	//Kundenreferenz
	@Column(name="customer")
	private String customer;

	/**
	 * @return the transactionType
	 */
	public String getTransactionType() {
		return transactionType;
	}

	/**
	 * @param transactionType the transactionType to set
	 */
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	/**
	 * @return the recipientInitiator
	 */
	public String getRecipientInitiator() {
		return recipientInitiator;
	}

	/**
	 * @param recipientInitiator the recipientInitiator to set
	 */
	public void setRecipientInitiator(String recipientInitiator) {
		this.recipientInitiator = recipientInitiator;
	}

	/**
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * @return the bankCode
	 */
	public String getBankCode() {
		return bankCode;
	}

	/**
	 * @param bankCode the bankCode to set
	 */
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	/**
	 * @return the creditorId
	 */
	public String getCreditorId() {
		return creditorId;
	}

	/**
	 * @param creditorId the creditorId to set
	 */
	public void setCreditorId(String creditorId) {
		this.creditorId = creditorId;
	}

	/**
	 * @return the mandate
	 */
	public String getMandate() {
		return mandate;
	}

	/**
	 * @param mandate the mandate to set
	 */
	public void setMandate(String mandate) {
		this.mandate = mandate;
	}

	/**
	 * @return the customer
	 */
	public String getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(String customer) {
		this.customer = customer;
	}
}
