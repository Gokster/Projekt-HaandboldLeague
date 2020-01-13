package entities;

import java.util.ArrayList;
import java.util.Date;

public class Match {
	private int matchId;
	private Team homeTeam;
	private Team awayTeam;
	private Team winningTeam;
	private Date matchDate;
	private MatchTime matchTime = new MatchTime();
	private ArrayList<Goal> goalList = new ArrayList<Goal>();
	private ArrayList<Suspension> suspensionList = new ArrayList<Suspension>();
	
	public Match(int matchId, Team homeTeam, Team awayTeam, Date matchDate, ArrayList<Goal> goalList, ArrayList<Suspension> suspensionList) {
		this.matchId = matchId;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.matchDate = matchDate;
		this.goalList = goalList;
		this.suspensionList = suspensionList;
	}
	public Match(Team homeTeam, Team awayTeam, Date matchDate) {
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.matchDate = matchDate;
	}
	private int countGoal(ArrayList<Goal> goalList, Team team) {
		int teamScore = 0;
		
		for(Goal goal: goalList ) {
			if(goal.getScoringTeam() == team) {
				teamScore++;
			}
		}
		return teamScore;	
	}
	
	private void calcWinningTeam() {
		int homeScore = countGoal(goalList, homeTeam);
		int awayScore = countGoal(goalList, awayTeam);
		
		if(homeScore > awayScore)	{
			winningTeam = homeTeam;
		} else if(homeScore < awayScore)	{
			winningTeam = awayTeam;
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
	
	private void addGoal(Team scoringTeam, MatchTime matchTime) {
		Goal goal = new Goal(scoringTeam, matchTime, matchId);
		goalList.add(goal);
	}
	
	private void deleteGoal() {
		Goal goal = goalList.get(goalList.size() - 1);
		goalList.remove(goal);
	}
	
	private void addSuspension(Team suspensionTeam, MatchTime matchTime) {
		Suspension suspension = new Suspension(suspensionTeam, matchTime, matchId);
		suspensionList.add(suspension);
	}
	
	private void deleteSuspension() {
		Suspension suspension = suspensionList.get(suspensionList.size() - 1);
		suspensionList.remove(suspension);
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