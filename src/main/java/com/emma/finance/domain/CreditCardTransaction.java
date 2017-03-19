package com.emma.finance.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CC")
public class CreditCardTransaction extends Transaction {
	
	//Urspruenglicher Betrag
	@Column(name="orininal_amount")
	private BigDecimal originalAmount;

}
