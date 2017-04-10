package com.emma.finance.domain;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="account_type")
@Table(name="fin_transaction")
public abstract class FinancialTransaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", updatable = false, nullable = false)
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
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the account
	 */
	public Account getAccount() {
		return account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(Account account) {
		this.account = account;
	}

	/**
	 * @return the valueDate
	 */
	public Date getValueDate() {
		return valueDate;
	}

	/**
	 * @param valueDate the valueDate to set
	 */
	public void setValueDate(Date valueDate) {
		this.valueDate = valueDate;
	}

	/**
	 * @return the bookingDate
	 */
	public Date getBookingDate() {
		return bookingDate;
	}

	/**
	 * @param bookingDate the bookingDate to set
	 */
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * @return the paymentDetails
	 */
	public String getPaymentDetails() {
		return paymentDetails;
	}

	/**
	 * @param paymentDetails the paymentDetails to set
	 */
	public void setPaymentDetails(String paymentDetails) {
		this.paymentDetails = paymentDetails;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
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

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FinancialTransaction other = (FinancialTransaction) obj;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TransactionStaging [id=" + id + ", account=" + account + ", valueDate=" + valueDate + ", bookingDate="
				+ bookingDate + ", amount=" + amount + ", paymentDetails=" + paymentDetails + "]";
	}
}
