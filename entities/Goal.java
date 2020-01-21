package entities;

public class Goal {
	
	private int matchId;
	private MatchTime timestamp;
	private Team scoringTeam;

	public Goal(Team scoringTeam, MatchTime matchTime, int matchId) {
		this.scoringTeam = scoringTeam;
		this.timestamp = matchTime;
		this.matchId = matchId;
	}
	
	/***********************************
	 * GETTERS
	 ***********************************/
	
	public Team getScoringTeam() {
		return scoringTeam;
	}

	public MatchTime getMatchTime() {
		return timestamp;
	}

	public int getMatchId() {
		return matchId;
	}
}
