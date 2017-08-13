package com.naga.kcomtest.service;

/**
 * The enum Coin of Denomination.
 * 
 * @author Naga
 *
 */
public enum Coin {
	ONE_POUND(100), FIFTY_PENCE(50), TWENTY_PENCE(20), TEN_PENCE(10), FIVE_PENCE(
			5), TWO_PENCE(2), ONCE_PENNY(1);

	private final Integer denomination;

	private Coin(final int denomination) {
		this.denomination = denomination;
	}

	public Integer getDenomination() {
		return this.denomination;
	}
	

}
