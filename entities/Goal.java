package entities;

public class Goal {
	
	private int goalId;
	private int matchId;
	private MatchTime timestamp;
	private Team scoringTeam;

	public Goal(int goalId, Team scoringTeam, MatchTime matchTime, int matchId) {
		this.goalId = goalId;
		this.scoringTeam = scoringTeam;
		this.timestamp = matchTime;
		this.matchId = matchId; 
	}

	public Goal(Team scoringTeam, MatchTime matchTime, int matchId) {
		this.scoringTeam = scoringTeam;
		this.timestamp = matchTime;
		this.matchId = matchId;
	}
	
	/***********************************
	 * GETTERS
	 ***********************************/

	public int getGoalId() {
		return goalId;
	}
	
	public Team getScoringTeam() {
		return scoringTeam;
	}

	public MatchTime getMatchTime() {
		return timestamp;
	}

	public int getMatchId() {
		return matchId;
	}
	
	/***********************************
	 * SETTERS
	 ***********************************/

	public void setGoalId(int goalId) {
		this.goalId = goalId;
	}

	public void setScoringTeam(Team scoringTeam) {
		this.scoringTeam = scoringTeam;
	}

	public void setMatchTime(MatchTime matchTime) {
		this.timestamp = matchTime;
	}

	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}	
}
