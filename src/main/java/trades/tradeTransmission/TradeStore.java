/**
 * 
 */
package trades.tradeTransmission;

import java.time.LocalDate;
import java.util.HashMap;

/**
 * Trade store class. Creation Date :05/22/2021
 * 
 * @author Ashwini Datir
 *
 */
public class TradeStore {

	public HashMap<String, Trades> tradeMap = new HashMap<String, Trades>();

	/*
	 * This method is to check if no trade Exists.
	 * 
	 * @return boolean
	 */
	public boolean checkIfTradeEmpty() {
		return tradeMap.isEmpty();
	}

	/*
	 * This method is used to check if the lower version is being received by the
	 * store it will reject the trade and throw an exception. If the version is same
	 * it will override the existing record.
	 * 
	 * @param trades
	 * 
	 * @param version
	 */
	public void checkVersion(Trades t, int version) throws Exception {
		if (t.getVersion() < version) {
			throw new Exception(t.getVersion() + " is less than " + version);

		}

	}

	/*
	 * This method is used to check if maturityDate less then today date.
	 * 
	 * @param maturityDate
	 * 
	 * @param currentDate return boolean
	 */
	public boolean checkMaturityDate(LocalDate maturityDate, LocalDate CurrentDate) {

		if (CurrentDate.compareTo(maturityDate) > 0)
			return false;

		return true;

	}

	/*
	 * This method is to check expired dates for trades.
	 */
	public void checkExpiredDates() {

		LocalDate currentDate = LocalDate.now();

		for (String strKey : tradeMap.keySet()) {
			if (currentDate.compareTo(tradeMap.get(strKey).getMaturityDate()) > 0) {
				Trades t = tradeMap.get(strKey);
				t.setExpired('Y');
				tradeMap.replace(strKey, t);
			}
		}

	}

	/*
	 * This method is to add new trade in store.
	 * 
	 * @param trades
	 */
	public void addTrade(Trades T) throws Exception {
		if (tradeMap.containsKey(T.getTradeId())) {
			checkVersion(T, tradeMap.get(T.getTradeId()).getVersion());

			if (checkMaturityDate(T.getMaturityDate(), tradeMap.get(T.getTradeId()).getMaturityDate())) {
				tradeMap.replace(T.getTradeId(), T);
				System.out.println(T.getTradeId() + " is added to the Store");
			} else {
				System.out.println("Unable to add " + T.getTradeId()
						+ " in the store as maturity date is lower than current date");
			}
		} else {

			if (checkMaturityDate(T.getMaturityDate(), T.getCreatedDate())) {

				tradeMap.put(T.getTradeId(), T);
				System.out.println(T.getTradeId() + " is added to the Store");

			} else {
				System.out.println("Unable to add " + T.getTradeId()
						+ " in the store as maturity date is lower than current date");
			}
		}

	}

	/*
	 * This method is to get trade from store.
	 * 
	 * @param tradeID
	 * 
	 * @return Trade object
	 */
	public Trades getTrade(String tradeID) throws Exception {
		if (tradeMap.containsKey(tradeID))
			return tradeMap.get(tradeID);
		throw new Exception("Trade with " + tradeID + " not Found");

	}

	/*
	 * This method is used to print the tread info.
	 */
	public void printTrade() {
		for (String tradeID : tradeMap.keySet()) {
			System.out.println(tradeMap.get(tradeID).toString());
		}
	}

}
