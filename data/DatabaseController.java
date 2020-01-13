package data;

import java.util.ArrayList;

import entities.Goal;
import entities.Match;
import entities.Suspension;
import entities.Team;

public class DatabaseController{
	public void createTeam(String teamName) {
		Team team = new Team(teamName);
		DataLayer dataLayer = new DataLayer();
		Teams teams = new Teams(dataLayer.getConnection());
		
		teams.createTeam(team);
	}
	public Team readTeamById(int id);
	public void updateTeam(Team team);
	public void deleteTeam(Team team);
	public ArrayList<Team> getAllTeams();
	
	public void createSuspension(Suspension suspension);
	public Team readSuspensionById(int id);
	public void updateSuspension(Suspension suspension);
	public void deleteSuspension(Suspension suspension);
	public ArrayList<Suspension> getAllSuspensions(int matchID);
	
	public void createMatch(Match match);
	public Team readMatchById(int id);
	public void updateMatch(Match match);
	public void deleteMatch(Match match);
	public ArrayList<Match> getAllMatches();
	
	public void createGoal(Goal goal);
	public Team readGoalById(int id);
	public void updateGoal(Goal goal);
	public void deleteGoal(Goal goal);
	public ArrayList<Goal> getAllGoals(int matchID);
}

