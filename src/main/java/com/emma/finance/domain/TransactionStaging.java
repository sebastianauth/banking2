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
@Table(name="transaction_staging")
public abstract class TransactionStaging {
	
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
}
