package com.emma.finance.batch;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.emma.finance.domain.GiroTransaction;

public class GiroFieldSetMapper implements FieldSetMapper<GiroTransaction> {

	@Override
	public GiroTransaction mapFieldSet(FieldSet fs) throws BindException {

		if(fs == null){
	           return null;
	    }
		
		GiroTransaction giroTransaction = new GiroTransaction();
		giroTransaction.setBookingDate(new java.sql.Date(fs.readDate("Buchungstag").getTime()));
		giroTransaction.setValueDate(new java.sql.Date(fs.readDate("Wertstellung").getTime()));
		giroTransaction.setTransactionType(fs.readString("Buchungstext"));
		giroTransaction.setRecipientInitiator(fs.readString("Auftraggeber"));
		giroTransaction.setPaymentDetails(fs.readString("Verwendungszweck"));
		giroTransaction.setAccountNumber(fs.readString("Kontonummer"));
		giroTransaction.setBankCode(fs.readString("BLZ"));
		giroTransaction.setAmount(fs.readBigDecimal("Betrag"));
		giroTransaction.setCreditorId(fs.readString("Glaeubiger-ID"));
		giroTransaction.setMandate(fs.readString("Mandatsreferenz"));
		giroTransaction.setCustomer(fs.readString("Kundenreferenz"));
		
		return giroTransaction;
	}

}
