package presentation;

public class SpecificMatchHistoryTable {

	private final String Home;
	private final String Time;
	private final String Away;

	public SpecificMatchHistoryTable(String home, String time, String away) {
		this.Home = new String(home);
		this.Time = new String(time);
		this.Away = new String(away);
	}
	
	/***********************************
	 * GETTERS
	 ***********************************/

	public String getHome() {
		return Home;
	}

	public String getTime() {
		return Time;
	}
	
	public String getTimeValue() {
		String minutes = "";
		int minutesVal;
		int secondsVal;
		int i = 0;
		
		while(Time.charAt(i) != ':') {
			minutes = minutes + Time.charAt(i);
			i++;
		}
		
		minutesVal = Integer.parseInt(minutes);
		secondsVal = minutesVal * 60 + Integer.parseInt(Time.substring(i + 1));
		
		return Integer.toString(secondsVal);
	}

	public String getAway() {
		return Away;
	}
}
