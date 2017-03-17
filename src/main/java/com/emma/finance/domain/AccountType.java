package com.emma.finance.domain;

public enum AccountType {
	
	GIRO_KONTO("Girokonto"),
	CREDIT_CARD("Kreditkarte");
	
	private String name;
	
	private AccountType(String name) {

		this.name = name;
	}

	public String getName() {
		return name;
	}

}
