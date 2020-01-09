package entities;

import java.sql.Time;
import java.util.Timer;

public class Goal {
	private int goalId;
	private int scoringTeam;
	private Time matchTime;
	private int matchId;
	
	public Goal(int goalId, int scoringTeam, Time matchTime, int matchId) {
		this.goalId = goalId;
		this.scoringTeam = scoringTeam;
		this.matchTime = matchTime;
		this.matchId = matchId; 
	}

	public void setGoalId(int goalId) {
		this.goalId = goalId;
	}
	
	public int getGoalId() {
		return goalId;
	}

	public int getScoringTeam() {
		return scoringTeam;
	}

	public Time getMatchTime() {
		return matchTime;
	}

	public int getMatchId() {
		return matchId;
	}
	
}
