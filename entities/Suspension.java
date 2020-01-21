package entities;

public class Suspension {

	private int matchId;
	private int suspensionId;
	private MatchTime timestamp;
	private Team suspensionTeam;
	
	public Suspension(int suspensionId, Team suspensionTeam, MatchTime matchTime, int matchId) {
		this.suspensionId = suspensionId;
		this.suspensionTeam = suspensionTeam;
		this.timestamp = matchTime;
		this.matchId = matchId;
	}
	
	public Suspension(Team suspensionTeam, MatchTime matchTime, int matchId) {
		this.suspensionTeam = suspensionTeam;
		this.timestamp = matchTime;
		this.matchId = matchId;
	}
	
	/***********************************
	 * GETTERS
	 ***********************************/
	
	public int getSuspensionId() {
		return suspensionId;
	}
	
	public Team getSuspensionTeam() {
		return suspensionTeam;
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

	public void setSuspensionId (int suspensionId) {
		this.suspensionId = suspensionId;
	}
}
