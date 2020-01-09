package entities;

import java.util.Date;

public class Team {
	private int teamID;
	private String teamName;
	private int teamPoints;
	
	public Team(int teamId, String teamName, int teamPoints) {
		this.teamID = teamId;
		this.teamName = teamName;
		this.teamPoints = teamPoints;
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

	public int getTeamPoints() {
		return teamPoints;
	}

	
	
	
}