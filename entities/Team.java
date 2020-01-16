package entities;

public class Team implements Comparable<Team>{
	private int teamId;
	private String teamName;
	private int teamPoints;
	private int ranking;
	
	public Team(int teamId, String teamName, int teamPoints, int ranking) {
		this.teamId = teamId;
		this.teamName = teamName;
		this.teamPoints = teamPoints;
		this.ranking = ranking;
	}

	public Team(int teamId, String teamName, int teamPoints) {
		this.teamId = teamId;
		this.teamName = teamName;
		this.teamPoints = teamPoints;
	}
	
	public Team(int teamId, String teamName) {
		this.teamId = teamId;
		this.teamName = teamName;
		this.teamPoints = 0;
	}
	
	public Team(String teamName) {
		this.teamName = teamName;
		this.teamPoints = 0;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public void setTeamId (int teamId) {
		this.teamId = teamId;
	}
	
	public int getTeamId() {
		return teamId;
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
	@Override
	public int compareTo(Team team) {
		return (this.getTeamPoints() < team.getTeamPoints() ? -1 : (this.getTeamPoints() == team.getTeamPoints() ? 0 : 1));
	}
	@Override
	public String toString() {
		return " Id: " + this.teamId + ", Team Name: " + this.teamName + ", Team Points:" + this.teamPoints + ", Ranking:" + this.ranking;
	}
}