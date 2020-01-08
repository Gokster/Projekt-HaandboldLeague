package entities;

import java.util.Date;

public class Team {
	private int teamID;
	private String teamName;
	private int teamPoints;
	
	public Team(int teamID, String teamName, int teamPoints) {
		this.teamID = teamID;
		this.teamName = teamName;
		this.teamPoints = teamPoints;
	}
}
