/**
 * 
 */
package trades.tradeTransmission;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * Junit Test case. Creation Date : 05/23/2021
 * 
 * @author Ashwini Datir
 *
 */
@TestMethodOrder // Order of Junit Testcases
(value = MethodOrderer.OrderAnnotation.class)

public class TradeStoreTest {

	/**
	 * 
	 */
	public TradeStoreTest() {
		// TODO Auto-generated constructor stub
	}

	TradeStore ts = new TradeStore();
	LocalDate todaysDate = LocalDate.now();
	LocalDate maturityDate = LocalDate.of(2021, 05, 23);
	LocalDate maturityDate1 = LocalDate.of(2020, 05, 20);

	/*
	 * Test case to validate weather trade store Empty or avail to add.
	 */
	@Test
	void testIfTradesEmpty() {
		assertTrue(ts.checkIfTradeEmpty());
	}

	@Test
	void testCheckVersion() {

	}

	/*
	 * Test case to Add first Trade.
	 */
	@Test
	void testAddTrades() throws Exception {
		Trades t1 = new Trades("T1", 1, "CP-1", "B1", LocalDate.of(2021, 05, 23), todaysDate, 'N');
		ts.addTrade(t1);
		assertEquals(1, ts.tradeMap.size());
	}

	/*
	 * Test case to Validate version trades added in store.
	 */
	@Test
	@Order(1)
	void testVersionHigh() throws Exception {
		Trades t2 = new Trades("T2", 2, "CP-2", "B1", maturityDate, todaysDate, 'N');
		ts.addTrade(t2);

		// Changing Version as 3 and Changing Counter-Party ID to CP-4
		Trades t3 = new Trades("T2", 5, "CP-4", "B1", maturityDate, todaysDate, 'N');
		ts.addTrade(t3);
		assertEquals("CP-4", ts.tradeMap.get("T2").getCounterPartyId());

	}

	/*
	 * Test case to validate if Version is low the Trades will be rejected T3 5 CP-3
	 * B1 20/05/2014 today date N T3 1 CP-2 B1 20/05/2014 today date N
	 * 
	 */
	@Test
	@Order(2)
	void testVersionLow() throws Exception {

		Trades t5 = new Trades("T3", 5, "CP-3", "B1", maturityDate, todaysDate, 'N');
		ts.addTrade(t5);
		int sizeofList = ts.tradeMap.size();
		// Now Adding Another List
		Trades t6 = new Trades("T3", 1, "CP-2", "B1", maturityDate, todaysDate, 'N');

		assertThrows(Exception.class, () -> ts.addTrade(t6), "1 is less than 5");

	}

	/*
	 * Validate if Version is same the list will be updated T1 1 CP-1 B1 20/05/2020
	 * today date N T1 1 CP-2 B1 20/05/2020 today date N
	 */
	@Test
	@Order(3)
	void testVersionSame() throws Exception {
		// Same Version as before and Changing Counter-Party ID to CP-2
		Trades t4 = new Trades("T1", 1, "CP-2", "B1", maturityDate, todaysDate, 'N');
		ts.addTrade(t4);
		assertEquals("CP-2", ts.tradeMap.get("T1").getCounterPartyId());
	}

	/*
	 * Validate if maturity Date is greater than todays date the Trades is added T4
	 * 5 CP-3 B1 20/05/2021 today date N
	 */
	@Test
	@Order(4)
	void testMaturityGreater() throws Exception {

		Trades t7 = new Trades("T4", 5, "CP-4", "B3", LocalDate.of(2021, 05, 25), todaysDate, 'N');
		ts.addTrade(t7);

		assertEquals(t7, ts.tradeMap.get("T4"));

	}

	/*
	 * Validate if maturity Date is lower than todays date the Trades will not be
	 * added T5 5 CP-3 B1 20/05/2020 today date N
	 */
	@Test
	@Order(5)
	void testMaurityLower() throws Exception {
		Trades t8 = new Trades("T5", 1, "CP-4", "B3", maturityDate1, todaysDate, 'N');
		ts.addTrade(t8);
		assertNull(ts.tradeMap.get("T5"));
	}

	/*
	 * Validate if Version is Same and date is lower the Trades is not updated T6 1
	 * CP-2 B1 20/05/2021 today date N T6 1 CP-2 B1 20/05/2020 today date N
	 */
	@Test
	@Order(6)
	void testMaturityLowerVersionSame() throws Exception {

		Trades t9 = new Trades("T6", 1, "CP-2", "B1", maturityDate, todaysDate, 'N');
		ts.addTrade(t9);
		Trades t10 = new Trades("T6", 1, "CP-2", "B1", maturityDate, todaysDate, 'N');
		ts.addTrade(t10);
		assertEquals(maturityDate, ts.tradeMap.get("T6").getMaturityDate());
	}

	/*
	 * Validate if Maturity Date is Same as Todays Date the list will be added T7 7
	 * CP-5 B4 todaysDate todaysDate N
	 */
	@Test
	@Order(7)
	void testSameMaturity() throws Exception {
		Trades t11 = new Trades("T7", 7, "CP-5", "B4", todaysDate, todaysDate, 'N');
		ts.addTrade(t11);
		assertNotNull(ts.tradeMap.get("T7"));
	}

	/*
	 * Validate if version is high but maturity date is low the Trades will be
	 * regected T8 1 CP-3 B1 20/05/2021 todaysDate N T8 5 CP-3 B1 20/05/2020
	 * todaysDate N
	 */
	@Test
	@Order(8)
	void testMaturitySameVersionMaturityLow() throws Exception {

		Trades t12 = new Trades("T8", 1, "CP-3", "B1", maturityDate, todaysDate, 'N');
		ts.addTrade(t12);
		// Now Adding Another List
		Trades t13 = new Trades("T8", 5, "CP-2", "B1", LocalDate.of(2020, 05, 20), todaysDate, 'N');
		assertEquals(1, ts.tradeMap.get("T8").getVersion());

	}

	/*
	 * Validate if both version and maturity low the Trades will not be added T8 1
	 * CP-3 B1 20/05/2021 todaysDate N T8 5 CP-3 B1 20/05/2020 todaysDate N1
	 */

	@Test
	@Order(9)
	void testVersionAndMaturityLow() throws Exception {
		Trades t14 = new Trades("T9", 5, "CP-3", "B1", LocalDate.of(2021, 05, 23), todaysDate, 'N');
		ts.addTrade(t14);
		// Now Adding Another List
		Trades t15 = new Trades("T9", 1, "CP-2", "B1", LocalDate.of(2020, 05, 20), todaysDate, 'N');
		assertThrows(Exception.class, () -> ts.addTrade(t15), "1 is less than 5");
	}

	/*
	 * Validate If Maturity Date is Expired it will update the Expired Flag.
	 * 
	 */
	@Test
	@Order(10)
	void testExpiry() {
		Trades t16 = new Trades("T10", 6, "CP-4", "B1", LocalDate.of(2020, 05, 20), todaysDate, 'N');
		ts.tradeMap.put("T10", t16);
		ts.checkExpiredDates();
		assertEquals('Y', ts.tradeMap.get("T10").getExpired());
	}

	/*
	 * Empty the HashMap to add / update given testcase from the table.
	 * 
	 */
	// @Test
	void removetradeMap() {
		ts.tradeMap.clear();
	}

	/*
	 * Validate the testcase for T1 1 CP-1 B1 20/05/2020 <today date> N Adding the
	 * Trades will fail so Validateing the size of the map to be empty.
	 * 
	 */
	@Test
	@Order(11)
	void testTradeStoreSize() throws Exception {
		Trades t17 = new Trades("T1", 1, "CP-1", "B1", LocalDate.of(2020, 05, 20), todaysDate, 'N');
		ts.addTrade(t17);
		assertEquals(0, ts.tradeMap.size());
	}

	/*
	 * Validate the testcase for T2 2 CP-2 B1 20/05/2021 <today date> N Adding the
	 * Trades will be added in the Trades map.
	 */
	@Test
	@Order(11)
	void testTrade2() throws Exception {
		Trades t18 = new Trades("T2", 2, "CP-2", "B1", maturityDate1, todaysDate, 'N');
		ts.addTrade(t18);
		assertEquals(0, ts.tradeMap.size());
	}

	/*
	 * Validate the testcase for T2 1 CP-1 B1 20/05/2021 14/03/2015 N Adding the
	 * Trades will not be added to the Trades list.
	 */
	@Test
	@Order(12)
	void testTradeAddException() throws Exception {
		Trades t18 = new Trades("T2", 2, "CP-2", "B1", maturityDate, todaysDate, 'N');
		ts.addTrade(t18);
		assertEquals(1, ts.tradeMap.size());
		Trades t19 = new Trades("T2", 1, "CP-2", "B1", maturityDate, LocalDate.of(2015, 03, 14), 'N');
		assertThrows(Exception.class, () -> ts.addTrade(t19));
	}

	@Test
	@Order(13)
	void testValidateExpiryDates() throws Exception {
		Trades t17 = new Trades("T1", 1, "CP-1", "B1", maturityDate, todaysDate, 'N');
		Trades t18 = new Trades("T2", 2, "CP-2", "B1", maturityDate, todaysDate, 'N');
		Trades t20 = new Trades("T3", 3, "CP-3", "B2", maturityDate1, todaysDate, 'N');
		ts.tradeMap.put("T3", t20);

		ts.checkExpiredDates();
		assertEquals('Y', ts.tradeMap.get("T3").getExpired());
	}

}
