/**
 * 
 */
package trades.tradeTransmission;

import java.time.LocalDate;

/**
 * Trade store Main class. Creation Date :05/22/2021
 * 
 * @author Ashwini Datir.
 *
 */
public class TradeTransmissionMain {

	/**
	 * Default constructor.
	 */
	public TradeTransmissionMain() {

	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		TradeStore ts = new TradeStore();
		LocalDate todaysDate = LocalDate.now();

		/*
		 * Add Trade T1 in store.
		 */
		Trades t1 = new Trades("T1", 1, "CP-1", "B1", LocalDate.of(2020, 05, 20), todaysDate, 'N');
		ts.addTrade(t1);

		/*
		 * Add Trade T2 in store.
		 */
		Trades t2 = new Trades("T2", 2, "CP-2", "B1", LocalDate.of(2021, 05, 20), todaysDate, 'N');
		ts.addTrade(t2);

		/*
		 * Add Trade T3 in store.
		 */
		LocalDate currentDate = LocalDate.of(2015, 03, 14);
		Trades t3 = new Trades("T4", 5, "CP-3", "B2", LocalDate.of(2021, 05, 20), currentDate, 'N');
		ts.addTrade(t3);

		/*
		 * Add Trade T4 in store.
		 */
		Trades t4 = new Trades("T3", 5, "CP-4", "B1", LocalDate.of(2014, 05, 20), todaysDate, 'Y');
		ts.addTrade(t4);

		/*
		 * Printing all trades info.
		 */
		System.out.println("\n\n");
		System.out.println("Displaying total number of Trade in the list");
		ts.printTrade();
		System.out.println("\n\n");

		// Validation for all Expired Flag
		System.out.println("Checking for Expired Flag");
		LocalDate maturityDate = LocalDate.of(2020, 05, 20);
		Trades t6 = new Trades("T2", 2, "CP-2", "B1", maturityDate, todaysDate, 'N');
		ts.tradeMap.replace("T2", t6);

		Trades t7 = new Trades("T4", 5, "CP-3", "B2", LocalDate.of(2020, 05, 20), todaysDate, 'N');
		ts.tradeMap.replace("T4", t7);
		ts.checkExpiredDates();
		ts.printTrade();

	}

}
