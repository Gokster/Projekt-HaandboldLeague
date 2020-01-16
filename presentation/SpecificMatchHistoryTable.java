package presentation;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

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
