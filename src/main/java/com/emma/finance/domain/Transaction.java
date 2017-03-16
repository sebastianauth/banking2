package com.emma.finance.domain;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Transaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(optional=false,fetch=FetchType.LAZY)
    @JoinColumn(name="account_fk")
	private Account account;
	
	//Wertstellung
	@Column(name="value_date")
	private Date valueDate;
	
	//Buchungstag, Belegdatum
	@Column(name="booking_date")
	private Date bookingDate;
	
	@Column
	private BigDecimal amount;
	
	//Verwendungszweck, Beschreibung
	@Column(name="payment_details")
	private String paymentDetails;
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((bookingDate == null) ? 0 : bookingDate.hashCode());
		result = prime * result + ((paymentDetails == null) ? 0 : paymentDetails.hashCode());
		result = prime * result + ((valueDate == null) ? 0 : valueDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (bookingDate == null) {
			if (other.bookingDate != null)
				return false;
		} else if (!bookingDate.equals(other.bookingDate))
			return false;
		if (paymentDetails == null) {
			if (other.paymentDetails != null)
				return false;
		} else if (!paymentDetails.equals(other.paymentDetails))
			return false;
		if (valueDate == null) {
			if (other.valueDate != null)
				return false;
		} else if (!valueDate.equals(other.valueDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Transaction [account=" + account + ", valueDate=" + valueDate + ", bookingDate=" + bookingDate
				+ ", amount=" + amount + ", paymentDetails=" + paymentDetails + "]";
	}
}
