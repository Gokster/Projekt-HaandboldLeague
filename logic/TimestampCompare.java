package logic;

import java.math.BigDecimal;
import java.util.Comparator;

import entities.MatchHistoryTable;

class TimestampCompare implements Comparator<MatchHistoryTable> {
	public int compare(MatchHistoryTable timestamp1, MatchHistoryTable timestamp2) {
		return new BigDecimal(timestamp1.getTimeValue()).compareTo(new BigDecimal(timestamp2.getTimeValue()));
	}
}
