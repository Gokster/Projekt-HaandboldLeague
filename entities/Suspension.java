package entities;

public class Suspension {

	private int matchId;
	private MatchTime timestamp;
	private Team suspensionTeam;
	
	public Suspension(Team suspensionTeam, MatchTime matchTime, int matchId) {
		this.suspensionTeam = suspensionTeam;
		this.timestamp = matchTime;
		this.matchId = matchId;
	}
	
	/***********************************
	 * GETTERS
	 ***********************************/
	
	public Team getSuspensionTeam() {
		return suspensionTeam;
	}

	public MatchTime getMatchTime() {
		return timestamp;
	}

	public int getMatchId() {
		return matchId;
	}
}
