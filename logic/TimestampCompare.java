package logic;

import java.math.BigDecimal;
import java.util.Comparator;

import presentation.SpecificMatchHistoryTable;

class TimestampCompare implements Comparator<SpecificMatchHistoryTable> {
	public int compare(SpecificMatchHistoryTable timestamp1, SpecificMatchHistoryTable timestamp2) {
		return new BigDecimal(timestamp1.getTimeValue()).compareTo(new BigDecimal(timestamp2.getTimeValue()));
	}
}
