package com.emma.finance.batch;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.emma.finance.domain.CreditCardTransaction;

public class CreditCardFieldSetMapper implements FieldSetMapper<CreditCardTransaction> {
	
	public static final String YES = "Ja";

	@Override
	public CreditCardTransaction mapFieldSet(FieldSet fs) throws BindException {

		if(fs == null){
	           return null;
	    }
		
		CreditCardTransaction ccTransaction = new CreditCardTransaction();
		
		String transactionCleared = fs.readString("Umsatz_abgerechnet");
		
		if (YES.equals(transactionCleared)) {
			ccTransaction.setTransactionCleared(Boolean.TRUE);
		} else {
			ccTransaction.setTransactionCleared(Boolean.FALSE);
		}
		
		ccTransaction.setBookingDate(new java.sql.Date(fs.readDate("Belegdatum").getTime()));
		ccTransaction.setValueDate(new java.sql.Date(fs.readDate("Wertstellung").getTime()));
		ccTransaction.setPaymentDetails(fs.readString("Beschreibung"));
		ccTransaction.setAmount(fs.readBigDecimal("Betrag"));
		ccTransaction.setOriginalAmount(fs.readBigDecimal("OrigBetrag"));
		
		return ccTransaction;
	}

}
