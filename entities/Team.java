package entities;

public class Team implements Comparable<Team>{
	
	private int ranking;
	private int teamId;
	private int teamPoints;
	private String teamName;

	public Team(int teamId, String teamName, int teamPoints) {
		this.teamId = teamId;
		this.teamName = teamName;
		this.teamPoints = teamPoints;
	}
	
	public Team(String teamName) {
		this.teamName = teamName;
		this.teamPoints = 0;
	}
	
	/***********************************
	 * COMPARE TO
	 ***********************************/
	
	@Override
	public int compareTo(Team team) {
		return (this.getTeamPoints() < team.getTeamPoints() ? -1 : (this.getTeamPoints() == team.getTeamPoints() ? 0 : 1));
	}
	
	/***********************************
	 * GETTERS
	 ***********************************/
	
	public int getRanking() {
		return ranking;
	}
	
	public int getTeamId() {
		return teamId;
	}
		
	public int getTeamPoints() {
		return teamPoints;
	}
	
	public String getTeamName() {
		return teamName;
	}
	
	/***********************************
	 * SETTERS
	 ***********************************/

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public void setTeamId (int teamId) {
		this.teamId = teamId;
	}
	
	public void setTeamPoints (int teamPoints) {
		this.teamPoints = teamPoints;
	}
}