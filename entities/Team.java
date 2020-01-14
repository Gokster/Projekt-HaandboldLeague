package entities;

public class Team {
	private int teamID;
	private String teamName;
	private int teamPoints;
	
	public Team(int teamId, String teamName, int teamPoints) {
		this.teamID = teamId;
		this.teamName = teamName;
		this.teamPoints = teamPoints;
	}
	
	public Team(int teamId, String teamName) {
		this.teamID = teamId;
		this.teamName = teamName;
		this.teamPoints = 0;
	}
	
	public Team(String teamName) {
		this.teamName = teamName;
		this.teamPoints = 0;
	}

	public void setTeamId (int teamId) {
		this.teamID = teamId;
	}
	
	public int getTeamId() {
		return teamID;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamPoints (int teamPoints) {
		this.teamPoints = teamPoints;
	}
	
	public int getTeamPoints() {
		return teamPoints;
	}	
}