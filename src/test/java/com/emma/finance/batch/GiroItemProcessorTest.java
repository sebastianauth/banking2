package com.emma.finance.batch;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.emma.finance.domain.GiroTransaction;

public class GiroItemProcessorTest {
	
	private GiroTransaction giroTransaction;
	
	@InjectMocks
    private GiroItemProcessor giroItemProcessor =
    new GiroItemProcessor();
	
	@Before
    public void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		
		giroTransaction = createMockGiroTransactions();
    }
	
	private GiroTransaction createMockGiroTransactions() {
		GiroTransaction gt = new GiroTransaction();
		gt.setPaymentDetails("Some details about the payment");
		gt.setAmount(BigDecimal.valueOf(103.34));
		return gt;
	}
	
    /**
    * Test method for {@link com.emma.finance.batch.GiroItemProcessor#process(com.emma.finance.domain.GiroTransaction)}.
    * @throws Exception
    */
    @Test
    public void testProcess() throws Exception {
    	
    	giroItemProcessor.process(giroTransaction);
    	Assert.assertNotNull(giroTransaction);

    }
}
