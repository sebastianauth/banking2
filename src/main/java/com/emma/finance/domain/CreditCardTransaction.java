package com.emma.finance.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CC")
public class CreditCardTransaction extends FinancialTransaction {
	
	//Umsatz abgerechnet
	@Column(name="ta_cleared")
	private Boolean transactionCleared;
	
	//Urspruenglicher Betrag
	@Column(name="original_amount")
	private BigDecimal originalAmount;

	/**
	 * @return the transactionCleared
	 */
	public Boolean getTransactionCleared() {
		return transactionCleared;
	}

	/**
	 * @param transactionCleared the transactionCleared to set
	 */
	public void setTransactionCleared(Boolean transactionCleared) {
		this.transactionCleared = transactionCleared;
	}

	/**
	 * @return the originalAmount
	 */
	public BigDecimal getOriginalAmount() {
		return originalAmount;
	}

	/**
	 * @param originalAmount the originalAmount to set
	 */
	public void setOriginalAmount(BigDecimal originalAmount) {
		this.originalAmount = originalAmount;
	}
}
