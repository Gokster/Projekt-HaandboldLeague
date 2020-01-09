package entities;

import java.sql.Time;

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

	public int getGoalId() {
		return goalId;
	}

	public void setGoalId(int goalId) {
		this.goalId = goalId;
	}

	public int getScoringTeam() {
		return scoringTeam;
	}

	public void setScoringTeam(int scoringTeam) {
		this.scoringTeam = scoringTeam;
	}

	public Time getMatchTime() {
		return matchTime;
	}

	public void setMatchTime(Time matchTime) {
		this.matchTime = matchTime;
	}

	public int getMatchId() {
		return matchId;
	}

	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}
	
}
