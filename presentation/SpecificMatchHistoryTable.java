package presentation;

public class SpecificMatchHistoryTable {

	private final String Home;
	private final String Time;
	private final String Away;

	SpecificMatchHistoryTable(String home, String time, String away) {
		this.Home = new String(home);
		this.Time = new String(time);
		this.Away = new String(away);
	}

	public String getHome() {
		return Home;
	}

	public String getTime() {
		return Time;
	}

	public String getAway() {
		return Away;
	}

}
