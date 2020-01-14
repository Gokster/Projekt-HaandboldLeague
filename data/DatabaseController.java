package data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;

import entities.Goal;
import entities.Match;
import entities.MatchTime;
import entities.Suspension;
import entities.Team;

public class DatabaseController {
	private DataLayer dataLayer = new DataLayer();
	private Teams teams = new Teams(dataLayer.getConnection());
	private Matches matches = new Matches(dataLayer.getConnection());
	private Suspensions suspensions = new Suspensions(dataLayer.getConnection());
	private Goals goals = new Goals(dataLayer.getConnection());

	public void createTeam(String teamName) {
		Team team = new Team(teamName);
		teams.createTeam(team);
	}

	public Team readTeamById(int id) {
		return teams.readTeamById(id);
	}

	public void deleteTeam(String teamName) { 
		teams.deleteTeam(teamName);
	}
	public ArrayList<Team> getAllTeams() {
		return teams.getAllTeams();
	}

	public void createMatch(Team homeTeam, Team awayTeam, Date date) {
		Match match = new Match(homeTeam, awayTeam, date);
		matches.createMatch(match);
	}

	public Match readMatchById(int id) {
		return matches.readMatchById(id);
	}

	public ArrayList<Match> getAllMatches() {
		return matches.getAllMatches();
	}

<<<<<<< HEAD
	public void createSuspension(int suspensionId, Team suspensionTeam, MatchTime timestamp, int matchId) {
		Suspension suspension = new Suspension(suspensionId, suspensionTeam, timestamp, matchId);
=======
	public void createSuspension(int suspensionId, Team suspensionTeam, MatchTime matchTime, int matchId) {
		Suspension suspension = new Suspension(suspensionId, suspensionTeam, matchTime, matchId);
>>>>>>> branch 'master' of https://github.com/Gokster/Projekt-HaandboldLeague.git
		suspensions.createSuspension(suspension);
	}

	public ArrayList<Suspension> getAllSuspensions(int matchId) {
		return suspensions.getAllSuspensions(matchId);
	}

<<<<<<< HEAD
	public void createGoal(int goalId, Team scoringTeam, MatchTime timestamp, int matchId) {
		Goal goal = new Goal(goalId, scoringTeam, timestamp, matchId);
=======
	public void createGoal(int goalId, Team scoringTeam, MatchTime matchTime, int matchId) {
		Goal goal = new Goal(goalId, scoringTeam, matchTime, matchId);
>>>>>>> branch 'master' of https://github.com/Gokster/Projekt-HaandboldLeague.git
		goals.createGoal(goal);
	}

	public ArrayList<Goal> getAllGoals(int matchId) {
		return goals.getAllGoals(matchId);
	}
}
