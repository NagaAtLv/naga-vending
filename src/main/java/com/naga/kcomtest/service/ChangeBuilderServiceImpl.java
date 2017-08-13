package com.naga.kcomtest.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import com.naga.kcomtest.service.util.FileProcessor;
import com.naga.kcomtest.service.util.FileProcessorUtils;
import com.naga.kcomtest.service.util.InsufficientCoinsException;

public class ChangeBuilderServiceImpl implements ChangeBuilderService {

	private List<Coin> coins;
	private FileProcessor fileProcessor = new FileProcessorUtils();
	private Properties properties;
	private String fileName;

	/**
	 * Default Constructor
	 */
	public ChangeBuilderServiceImpl() {

	}

	/**
	 * Constructor with fileName to load properties
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	public ChangeBuilderServiceImpl(String fileName) throws IOException {
		this.fileName = fileName;
		this.properties = this.fileProcessor.readFile(this.fileName);

	}

	/***
	 * Implementation of a getOptimalChangeFor method for a vending machine that
	 * will return the optimal change for a given number of pence.
	 */
	@Override
	public Collection<Coin> getOptimalChangeFor(int pence) {

		this.coins = new ArrayList<Coin>();

		for (final Coin coin : Coin.values()) {
			pence = getAllCoins(pence, coin);
		}
		return this.coins;

	}

	private int getAllCoins(int pence, final Coin coin) {
		final int number = pence / coin.getDenomination();
		for (int i = 0; i < number; i++) {
			this.coins.add(coin);
		}
		return pence % coin.getDenomination();
	}

	/**
	 * Implementation of a getChangeFor method to get the change for a given
	 * number of pence based on a limited supply of coins in Properties.
	 */
	@Override
	public Collection<Coin> getChangeFor(int pence) {

		this.coins = new ArrayList<Coin>();

		for (final Coin coin : Coin.values()) {
			pence = getCoinsFromProp(pence, coin);
		}

		this.fileProcessor.writeFile(this.fileName, this.properties);
		return this.coins;

	}

	/**
	 * Private method to get Coins from Property file.
	 * 
	 * @param pence
	 * @param coin
	 * @return numberOfcoins
	 */
	private int getCoinsFromProp(final int pence, final Coin coin) {
		int remainder = pence;
		Integer quantity = Integer.parseInt((String) this.properties.get(coin
				.getDenomination().toString()));
		while (remainder >= coin.getDenomination() && quantity > 0) {
			this.coins.add(coin);
			remainder -= coin.getDenomination();
			quantity--;
			this.properties.setProperty(coin.getDenomination().toString(),
					quantity.toString());
		}
		if(remainder >= coin.getDenomination() && quantity== 0){
			throw new InsufficientCoinsException(" Insufficient Coins in Inventory");
		}
		return remainder;

	}

}
