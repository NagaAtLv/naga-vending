package com.naga.kcomtest.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import com.naga.kcomtest.service.util.FileProcessor;
import com.naga.kcomtest.service.util.FileProcessorUtils;
import com.naga.kcomtest.service.util.InsufficientCoinsException;

/**
 * Test for {@link ChangeBuilderService}. This Test class test the limited Coin
 * collection as well as unlimited supply of coins.
 * 
 * @author Naga
 *
 */
public class ChangeBuilderServiceTest {

	private ChangeBuilderService changeBuilderService;
	private FileProcessor fileProcessor;
	private static final String TEST_FILENAME = "resources/test-coin-inventory.properties";

	/**
	 * Set up method, we may get IOException when initialising
	 * 
	 * @throws IOException
	 */
	@Before
	public void set() throws IOException {
		/* Default implementation Sufficient for Optimal Change requirement */
		this.changeBuilderService = new ChangeBuilderServiceImpl();
		this.fileProcessor = new FileProcessorUtils();

	}

	/***
	 * This test method coverage all possible Coin denomination's
	 * 
	 * @throws IOException
	 */
	@Test
	public void testGetChangeFor() throws IOException {

		this.mockProperties(TEST_FILENAME);
		this.resetService();
		final Collection<Coin> coins = this.changeBuilderService
				.getChangeFor(232);

		assertThat(coins, is(notNullValue()));
		assertThat(coins.size(), equalTo(5));
		assertTrue(coins.contains(Coin.ONE_POUND));
		assertTrue(coins.contains(Coin.TWENTY_PENCE));
		assertTrue(coins.contains(Coin.TEN_PENCE));
		assertTrue(coins.contains(Coin.TWO_PENCE));

		assertFalse(coins.contains(Coin.FIFTY_PENCE));
		assertFalse(coins.contains(Coin.ONCE_PENNY));
		assertFalse(coins.contains(Coin.FIVE_PENCE));

	}

	/***
	 * This test method catches NullPointer when no filename supplied.
	 * 
	 * @throws IOException
	 */
	@Test(expected = NullPointerException.class)
	public void testGetChangeForonNullFileName() throws IOException {
		this.mockNegProperties(null);

	}

	/***
	 * Resetting old properties
	 * 
	 * @throws IOException
	 */
	private void resetService() throws IOException {
		this.changeBuilderService = null;
		// this.fileProcessor = new FileProcessorUtils();

		this.changeBuilderService = new ChangeBuilderServiceImpl(TEST_FILENAME);

	}

	/***
	 * This test method coverage Negative Scenario's of Optimal function
	 * 
	 * @throws IOException
	 */
	@Test(expected = InsufficientCoinsException.class)
	public void testNegativeGetChangeFor() throws IOException {
		/* Overriding properties in Service class to reflect this scenario */
		this.resetService();
		this.mockNegProperties(TEST_FILENAME);

		this.changeBuilderService = new ChangeBuilderServiceImpl(TEST_FILENAME);
		this.changeBuilderService.getChangeFor(1099);

	}

	/***
	 * This test method coverage all possible Coin denomination's of Optimal
	 * function
	 */
	@Test
	public void testGetOptimalChangeFor() {
		final Collection<Coin> coins = this.changeBuilderService
				.getOptimalChangeFor(188);

		assertThat(coins, is(notNullValue()));
		assertThat(coins.size(), CoreMatchers.equalTo(7));
		assertTrue(coins.contains(Coin.ONE_POUND));
		assertTrue(coins.contains(Coin.FIFTY_PENCE));
		assertTrue(coins.contains(Coin.TWENTY_PENCE));
		assertTrue(coins.contains(Coin.TEN_PENCE));
		assertTrue(coins.contains(Coin.FIVE_PENCE));
		assertTrue(coins.contains(Coin.TWO_PENCE));
		assertTrue(coins.contains(Coin.ONCE_PENNY));

	}

	/***
	 * This test method coverage Negative Senario's of Optimal function
	 */
	@Test
	public void testNegativeGetOptimalChangeFor() {
		final Collection<Coin> coins = this.changeBuilderService
				.getOptimalChangeFor(102);

		assertThat(coins, is(notNullValue()));
		assertThat(coins.size(), CoreMatchers.equalTo(2));
		assertTrue(coins.contains(Coin.ONE_POUND));
		assertTrue(coins.contains(Coin.TWO_PENCE));

		/* Not contains below denomination's */
		assertFalse(coins.contains(Coin.FIFTY_PENCE));
		assertFalse(coins.contains(Coin.TWENTY_PENCE));
		assertFalse(coins.contains(Coin.TEN_PENCE));
		assertFalse(coins.contains(Coin.FIVE_PENCE));
		assertFalse(coins.contains(Coin.ONCE_PENNY));

	}

	/***
	 * Test Properties
	 * 
	 * @param TEST_FILENAME
	 * @throws IOException
	 */
	private void mockProperties(final String TEST_FILENAME) throws IOException {
		final Properties properties = new Properties();
		properties.setProperty("100", "14");
		properties.setProperty("50", "25");
		properties.setProperty("20", "10");
		properties.setProperty("10", "120");
		properties.setProperty("5", "26");
		properties.setProperty("2", "30");
		properties.setProperty("1", "17");
		this.fileProcessor.writeFile(TEST_FILENAME, properties);
	}

	/***
	 * Loading test Properties for Negative test
	 * 
	 * @param TEST_FILENAME
	 * @throws IOException
	 */
	private void mockNegProperties(final String TEST_FILENAME)
			throws IOException {
		final Properties properties = new Properties();
		properties.setProperty("100", "1");
		properties.setProperty("50", "2");
		properties.setProperty("20", "5");
		properties.setProperty("10", "0");
		properties.setProperty("5", "0");
		properties.setProperty("2", "12");
		properties.setProperty("1", "10");
		this.fileProcessor.writeFile(TEST_FILENAME, properties);
	}

}
