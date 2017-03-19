package com.emma.finance.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("GIRO")
public class GiroTransaction extends Transaction {
	
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

}
