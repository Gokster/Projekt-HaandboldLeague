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

	public int getGoalID() {
		return goalID;
	}

	public void setGoalID(int goalID) {
		this.goalID = goalID;
	}

	public int getScoringTeam() {
		return scoringTeam;
	}

	public void setScoringTeam(int scoringTeam) {
		this.scoringTeam = scoringTeam;
	}

	public Timer getMatchTime() {
		return matchTime;
	}

	public void setMatchTime(Timer matchTime) {
		this.matchTime = matchTime;
	}

	public int getMatchID() {
		return matchID;
	}

	public void setMatchID(int matchID) {
		this.matchID = matchID;
	}
	
}
