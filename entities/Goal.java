package entities;

import java.sql.Time;

public class Goal {
	private int goalId;
	private int scoringTeam;
	private int matchTime;
	private int matchId;
	
	// 09-01-2020 Ændret matchTime fra typen Time til int
	public Goal(int goalId, int scoringTeam, int matchTime, int matchId) {
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
