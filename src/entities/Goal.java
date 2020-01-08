package entities;

import java.util.Timer;

public class Goal {
	private int goalID;
	private int scoringTeam;
	private Timer matchTime;
	private int matchID;
	
	public Goal(int goalID, int scoringTeam, Timer matchTime, int matchID) {
		this.goalID = goalID;
		this.scoringTeam = scoringTeam;
		this.matchTime = matchTime;
		this.matchID = matchID; 
	}
}
