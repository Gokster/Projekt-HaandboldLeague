package data;

import java.util.ArrayList;

import entities.Goal;
import entities.Match;
import entities.Suspension;
import entities.Team;

interface DBInterface{
	public void createTeam(Team team);
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

public class DatabaseImplementation implements DBInterface {

	@Override
	public void createTeam(Team team) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Team readTeamById(int id) {
		// TODO Auto-generated method stub
		return Team;
	}

	@Override
	public void updateTeam(Team team) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteTeam(Team team) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Team> getAllTeams() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createSuspension(Suspension suspension) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Team readSuspensionById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateSuspension(Suspension suspension) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteSuspension(Suspension suspension) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Suspension> getAllSuspensions(int matchID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createMatch(Match match) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Team readMatchById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateMatch(Match match) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMatch(Match match) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Match> getAllMatches() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createGoal(Goal goal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Team readGoalById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateGoal(Goal goal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteGoal(Goal goal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Goal> getAllGoals(int matchID) {
		// TODO Auto-generated method stub
		return null;
	}

}
