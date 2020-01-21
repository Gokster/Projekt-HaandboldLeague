package data;

import java.util.ArrayList;
import java.sql.Date;
import entities.Goal;
import entities.Match;
import entities.Suspension;
import entities.Team;

public class DatabaseController {
	private DataLayer dataLayer = new DataLayer();
	private Teams teams = new Teams(dataLayer.getConnection());
	private Matches matches = new Matches(dataLayer.getConnection());
	private Suspensions suspensions = new Suspensions(dataLayer.getConnection());
	private Goals goals = new Goals(dataLayer.getConnection());

	/***********************************
	 * TEAMS
	 ***********************************/
	
	public void createTeam(String teamName) {
		Team team = new Team(teamName);
		teams.createTeam(team);
	}

	public ArrayList<Team> getAllTeams() {
		return teams.readAllTeams();
	}

	public void deleteTeam(String teamName) { 
		ArrayList<Match> matchList = getAllMatchesNotDone();
		
		for(Match match : matchList) {
			if(teamName.compareTo(match.getHomeTeam().getTeamName()) == 0) {
				deleteMatch(match);
			} else if (teamName.compareTo(match.getAwayTeam().getTeamName()) == 0) {
				deleteMatch(match);
			}
		}
		teams.deleteTeam(teamName);
	}
	
	/***********************************
	 * MATCHES
	 ***********************************/

	public void createMatch(Team homeTeam, Team awayTeam, Date date) {
		Match match = new Match(homeTeam, awayTeam, date);
		matches.createMatch(match);
	}
	
	public ArrayList<Match> getAllMatchesNotDone() {
		ArrayList<Team> teamList = teams.readAllTeams();
		ArrayList<Goal> goalList = goals.getAllGoals(teamList);
		ArrayList<Suspension> suspensionList = suspensions.getAllSuspensions(teamList);
		return matches.getAllMatches(teamList, goalList, suspensionList);
	}
	
	public void updateCurrentMatch(Match match) {
		matches.updateMatch(match);
	}
	
	public void deleteMatch(Match match) { 
		matches.deleteMatch(match);
	}

//	public ArrayList<Match> getAllMatches() {
//		ArrayList<Team> teamList = teams.readAllTeams();
//		return matches.getAllMatches(teamList);
//	}
	
	/***********************************
	 * SUSPENSIONS
	 ***********************************/
	
	public void createSuspensions(ArrayList<Suspension> suspensionList) {
		for(Suspension suspension : suspensionList) {
			suspensions.createSuspension(suspension);
		}
	}
	
	/***********************************
	 * GOALS
	 ***********************************/
	
	public void createGoals(ArrayList<Goal> goalList) {
		for(Goal goal : goalList) {
			goals.createGoal(goal);
		}
	}
}
