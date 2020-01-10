package entities;

import java.sql.Time;

public class Goal {
	private int goalId;
	private Team scoringTeam;
	private int matchTime;
	private int matchId;
	
	// 09-01-2020 �ndret matchTime fra typen Time til int
	public Goal(int goalId, Team scoringTeam, int matchTime, int matchId) {
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

	public Team getScoringTeam() {
		return scoringTeam;
	}

	public void setScoringTeam(Team scoringTeam) {
		this.scoringTeam = scoringTeam;
	}

	public int getMatchTime() {
		return matchTime;
	}

	public void setMatchTime(int matchTime) {
		this.matchTime = matchTime;
	}

	public int getMatchId() {
		return matchId;
	}

	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}
	
}
