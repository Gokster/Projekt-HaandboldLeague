package entities;

import java.util.Date;

public class Match {
	private int matchId;
	private Team homeTeam;
	private Team awayTeam;
	private Team winningTeam;
	private Date matchDate;
	
	public Match(int matchId, Team homeTeam, Team awayTeam, Team winningTeam, Date matchDate) {
		this.matchId = matchId;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.winningTeam = winningTeam;
		this.matchDate = matchDate;
	}
	Goal goalArr[] = new Goal[1000];

	private int countGoal(Goal[] goalArr, Team team) {
		int teamScore = 0;
		
		for(Goal goal: goalArr ) {
			if(goal.getScoringTeam() == team.getTeamId()) {
				teamScore++;
			}
		}
		return teamScore;	
	}
	
	private void calcWinningTeam() {
		int homeScore = countGoal(goalArr, homeTeam);
		int awayScore = countGoal(goalArr, awayTeam);
		
		if(homeScore > awayScore)	{
			winningTeam = homeTeam;
		} else if(homeScore < awayScore)	{
			winningTeam = awayTeam;
		} else	{
			winningTeam = new Team(-1, "draw");
		}
	}
	
	private void giveTeamPoints() {
		if(winningTeam == homeTeam)		{
			homeTeam.setTeamPoints(homeTeam.getTeamPoints() + 2);
		} else if (winningTeam == awayTeam)	{
			awayTeam.setTeamPoints(awayTeam.getTeamPoints() + 2);
		} else	{
			homeTeam.setTeamPoints(homeTeam.getTeamPoints() + 1);
			awayTeam.setTeamPoints(awayTeam.getTeamPoints() + 1);
		}
	}
	
	public void setMatchId (int matchId) {
		this.matchId = matchId;
	}

	public int getMatchId() {
		return matchId;
	}

	public Team getHomeTeam() {
		return homeTeam;
	}

	public Team getAwayTeam() {
		return awayTeam;
	}

	public Team getWinningTeam() {
		return winningTeam;
	}

	public Date getMatchDate() {
		return matchDate;
	}
	
	
}