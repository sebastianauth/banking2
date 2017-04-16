package com.emma.finance.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.emma.finance.domain.Account;
import com.emma.finance.domain.AccountType;
import com.emma.finance.domain.GiroTransaction;

/**
 * This custom {@code ItemProcessor} adds the account type and writes 
 * the information of the processed transaction to the log and returns the processed object.
 *
 * @author Sebastian
 */
public class GiroItemProcessor implements ItemProcessor<GiroTransaction, GiroTransaction> {
	
	private static final Logger log = LoggerFactory.getLogger(GiroItemProcessor.class);

	@Override
	public GiroTransaction process(GiroTransaction item) throws Exception {
		
		Account account = new Account();
		account.setAccountType(AccountType.GIRO_KONTO);
		
		item.setAccount(account);
		
		log.info("Processing transaction information: {}", item);
		
		return item;
	}

}
