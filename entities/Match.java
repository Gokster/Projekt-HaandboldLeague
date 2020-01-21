package entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;

public class Match {
	
	private int matchId;
	private int winningTeam;
	private int matchDateInt;
	private Date matchDate;
	private MatchTime matchTime = new MatchTime();
	private Team homeTeam;
	private Team awayTeam;
	private ArrayList<Goal> goalList = new ArrayList<Goal>();
	private ArrayList<Suspension> suspensionList = new ArrayList<Suspension>();

	public Match(int matchId, Team homeTeam, Team awayTeam, int winningTeam, Date matchDate, ArrayList<Goal> goalList,
			ArrayList<Suspension> suspensionList) {
		this.matchId = matchId;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.winningTeam = winningTeam;
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

	public Match(Team homeTeam, Team awayTeam, int date) {
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.matchDateInt = date;
	}
	
	/***********************************
	 * CALCULATORS
	 ***********************************/

	public int countGoal(Team team) {
		int teamScore = 0;

		for (Goal goal : goalList) {
			if (goal.getScoringTeam() == team) {
				teamScore++;
			}
		}
		return teamScore;
	}
	
	public void calcWinningTeam() {
		int homeScore = countGoal(homeTeam);
		int awayScore = countGoal(awayTeam);

		if (homeScore > awayScore) {
			winningTeam = 1;
		} else if (homeScore < awayScore) {
			winningTeam = 2;
		} 
		else if (homeScore == awayScore){
			winningTeam = 3;
		}
	}
	
	/***********************************
	 * EVENTS
	 ***********************************/

	public void addGoal(Team scoringTeam, long currentTime) {
		MatchTime timeStamp = new MatchTime((int) currentTime);
		Goal goal = new Goal(scoringTeam, timeStamp, matchId);
		goalList.add(goal);
	}

	public void deleteGoal(Team team) {
		Goal goal;
		
		for (int i = goalList.size() - 1; i > 0; i--) {
			goal = goalList.get(i);

			if (goal.getScoringTeam() == team) {
				goalList.remove(goal);
				break;
			}
		}
	}
 
	public void addSuspension(Team suspensionTeam, long currentTime) {
		MatchTime timeStamp = new MatchTime((int) currentTime);
		Suspension suspension = new Suspension(suspensionTeam, timeStamp, matchId);
		suspensionList.add(suspension);
	}

	public void deleteSuspension(Team team) {
		Suspension suspension;

		for (int i = suspensionList.size() - 1; i > 0; i--) {
			suspension = suspensionList.get(i);

			if (suspension.getSuspensionTeam() == team) {
				suspensionList.remove(suspension);
				break;
			}
		}
	}
	
	public void startMatch() {
		matchTime.startMatchTimer();
	}	
	
	/***********************************
	 * COMPARATOR
	 ***********************************/

	public static Comparator<Match> dateCompare = new Comparator<Match>() {
		@Override
		public int compare(Match m1, Match m2) {
			return m1.getMatchDate().compareTo(m2.getMatchDate());
		}
	};
	
	/***********************************
	 * GIVE TEAM POINTS
	 ***********************************/
	
	private void giveTeamPoints() {
		if (winningTeam == 1) {
			homeTeam.setTeamPoints(homeTeam.getTeamPoints() + 2);
		} else if (winningTeam == 2) {
			awayTeam.setTeamPoints(awayTeam.getTeamPoints() + 2);
		} else if (winningTeam == 3) {
			homeTeam.setTeamPoints(homeTeam.getTeamPoints() + 1);
			awayTeam.setTeamPoints(awayTeam.getTeamPoints() + 1);
		}
	}
	
	/***********************************
	 * GETTERS
	 ***********************************/

	public int getMatchId() {
		return matchId;
	}
	
	public long getMatchSeconds() {
		matchTime.getMatchTime();
		return matchTime.calcSeconds();
	}
	
	public Team getHomeTeam() {
		return homeTeam;
	}
	
	public Team getAwayTeam() {
		return awayTeam;
	}

	public int getWinningTeam() {
		return winningTeam;
	}

	public Date getMatchDate() {
		return matchDate;
	}

	public int getMatchDateInt() {
		return matchDateInt;
	}

	public ArrayList<Goal> getGoalList() {
		return goalList;
	}

	public ArrayList<Suspension> getSuspensionList() {
		return suspensionList;
	}
	
	/***********************************
	 * SETTERS
	 ***********************************/

	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}
}