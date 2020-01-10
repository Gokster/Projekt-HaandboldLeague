package entities;

import java.sql.Time;
import java.util.Timer;

public class Suspension {

	private int suspensionId;
	private Team suspensionTeam;
	private int matchTime;
	private int matchId;
	
	// 09-01-2020 �ndret matchTime fra typen Time til int
	public Suspension(int suspensionId, Team suspensionTeam, int matchTime, int matchId) {
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

	public Team getSuspensionTeam() {
		return suspensionTeam;
	}

	public int getMatchTime() {
		return matchTime;
	}

	public int getMatchId() {
		return matchId;
	}
	
	
}
