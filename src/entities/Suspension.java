package entities;

import java.util.Timer;

public class Suspension {

	private int suspensionID;
	private int suspensionTeam;
	private Timer matchTime;
	private int matchID;
	
	public Suspension(int suspensionID, int suspensionTeam, Timer matchTime, int matchID) {
		this.suspensionID = suspensionID;
		this.suspensionTeam = suspensionTeam;
		this.matchTime = matchTime;
		this.matchID = matchID;
	}
}
