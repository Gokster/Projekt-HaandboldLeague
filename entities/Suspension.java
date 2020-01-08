package entities;

import java.util.Timer;

public class Suspension {

	private int suspensionId;
	private int suspensionTeam;
	private Timer matchTime;
	private int matchId;
	
	public Suspension(int suspensionId, int suspensionTeam, Timer matchTime, int matchId) {
		this.suspensionId = suspensionId;
		this.suspensionTeam = suspensionTeam;
		this.matchTime = matchTime;
		this.matchId = matchId;
	}

	public int getSuspensionId() {
		return suspensionId;
	}

	public int getSuspensionTeam() {
		return suspensionTeam;
	}

	public Timer getMatchTime() {
		return matchTime;
	}

	public int getMatchId() {
		return matchId;
	}
	
	
}
