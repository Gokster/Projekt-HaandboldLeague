package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;

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
	public Match(int matchId, Team homeTeam, Team awayTeam, Date matchDate) {
		this.matchId = matchId;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.matchDate = matchDate;
	}
	public Match(Team homeTeam, Team awayTeam, Date date) {
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.matchDate = date;
	}	
	
	public int countGoal(Team team) {
		int teamScore = 0;
		
		for(Goal goal: goalList ) {
			if(goal.getScoringTeam() == team) {
				teamScore++;
			}
		}
		return teamScore;	
	}
	
	private void calcWinningTeam() {
		int homeScore = countGoal(homeTeam);
		int awayScore = countGoal(awayTeam);
		
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
	
	public void addGoal(Team scoringTeam) {
		MatchTime timeStamp = new MatchTime(matchTime);
		Goal goal = new Goal(scoringTeam, timeStamp, matchId);
		goalList.add(goal);
	}
	
	public void deleteGoal(Team team) {
		Goal goal;
		
		for (int i = goalList.size() - 1; i > 0; i--) {
			goal = goalList.get(i);
			
			if(goal.getScoringTeam() == team) {
				goalList.remove(goal);
				break;
			}
		}
	}
	
	public void addSuspension(Team suspensionTeam) {
		MatchTime timeStamp = new MatchTime(matchTime);
		Suspension suspension = new Suspension(suspensionTeam, timeStamp, matchId);
		suspensionList.add(suspension);
	}
	
	public void deleteSuspension(Team team) {
		Suspension suspension; 
		
		for (int i = suspensionList.size() - 1; i > 0; i--) {
			suspension = suspensionList.get(i);
			
			if(suspension.getSuspensionTeam() == team) {
				suspensionList.remove(suspension);
				break;
			}
		}
	}
	
	public void startMatch(int matchLength) {
		matchTime.startMatchTimer();
		while (matchTime.getMinutes() < matchLength) {
			matchTime.getMatchTime();
		}
		matchTime.stopMatchTimer();
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
	public ArrayList<Goal> getGoalList() {
		return goalList;
	}
	public ArrayList<Suspension> getSuspensionList() {
		return suspensionList;
	}
	
}