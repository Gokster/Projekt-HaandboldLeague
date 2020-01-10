package entities;

import java.sql.Time;
import java.util.Timer;

public class Suspension {

	private int suspensionId;
	private int suspensionTeam;
	private Time matchTime;
	private int matchId;
	
	// 09-01-2020 Ændret matchTime fra typen Time til int
	public Suspension(int suspensionId, int suspensionTeam, Time matchTime, int matchId) {
		this.suspensionId = suspensionId;
		this.suspensionTeam = suspensionTeam;
		this.matchTime = matchTime;
		this.matchId = matchId;
	}

	public void setSuspensionId (int suspensionId) {
		this.suspensionId = suspensionId;
	}

	public int getSuspensionId() {
		return suspensionId;
	}

	public int getSuspensionTeam() {
		return suspensionTeam;
	}

	public Time getMatchTime() {
		return matchTime;
	}

	public int getMatchId() {
		return matchId;
	}
	
	
}
