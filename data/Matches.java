package data;

import java.sql.*;
import java.util.ArrayList;
import entities.Team;
import entities.Goal;
import entities.Match;
import entities.Suspension;

public class Matches {
	private Connection connection;
	private Goals goals = new Goals(connection);
	private Suspensions suspensions = new Suspensions(connection);
	
	public Matches(Connection connection) {
		this.connection = connection;
	}
	
	/***********************************
	 * CREATE
	 ***********************************/

	public void createMatch (Match match) {
		try {
			String sql = "INSERT INTO matches VALUES (" + match.getHomeTeam().getTeamId() + ", " + match.getAwayTeam().getTeamId() + ", NULL, '" + match.getMatchDate() + "')";

			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);

			ResultSet resultSet = statement.executeQuery("SELECT SCOPE_IDENTITY()");
			resultSet.next();
			match.setMatchId(resultSet.getInt(1));
		} catch (SQLException e) {
			System.out.println("GSFDSGD"); 
			e.printStackTrace();
		}
	}
	
	/***********************************
	 * READ
	 ***********************************/
	
	public ArrayList<Match> getAllMatches(ArrayList<Team> teamList) {
		ArrayList<Match> matchesList = new ArrayList<>();
		ArrayList<Goal> goalList = goals.getAllGoals(teamList);
		ArrayList<Suspension> suspensionList = suspensions.getAllSuspensions(teamList);

		try {
			String sql = "SELECT * FROM matches";

			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				Team homeTeam = null;
				for (int i = 0; i < teamList.size(); i++) {
					if (teamList.get(i).getTeamId() == resultSet.getInt("hometeam")) {
						homeTeam = teamList.get(i);
						break;
					}
				}
				
				Team awayTeam = null;
				for (int i = 0; i < teamList.size(); i++) {
					if (teamList.get(i).getTeamId() == resultSet.getInt("awayteam")) {
						homeTeam = teamList.get(i);
						break;
					}
				}
				
				ArrayList<Goal> matchGoalList = new ArrayList<Goal>();
				for(Goal goal : goalList) {
					if(id == goal.getMatchId()) {
						matchGoalList.add(goal);
					}
				}
				
				ArrayList<Suspension> matchSuspensionList = new ArrayList<Suspension>();
				for(Suspension suspension : suspensionList) {
					if(id == suspension.getMatchId()) {
						matchSuspensionList.add(suspension);
					}
				}
				
				Date matchDate = resultSet.getDate("matchdate");
				
				Match match = new Match(id, homeTeam, awayTeam, matchDate, goalList, suspensionList);

				matchesList.add(match);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return matchesList;
	}
	
	
	public ArrayList<Match> getAllMatchesNotPlayed(ArrayList<Team> teamList) {
		ArrayList<Match> matchesList = new ArrayList<>();

		try {
			String sql = "SELECT * FROM matches";

			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				Team homeTeam = null;
				for (int i = 0; i < teamList.size(); i++) {
					if (teamList.get(i).getTeamId() == resultSet.getInt("hometeam")) {
						homeTeam = teamList.get(i);
						break;
					}
				}
				
				Team awayTeam = null;
				for (int i = 0; i < teamList.size(); i++) {
					if (teamList.get(i).getTeamId() == resultSet.getInt("awayteam")) {
						awayTeam = teamList.get(i);
						break;
					}
				}				
				Date matchDate = resultSet.getDate("matchdate");
				
				Match match = new Match(id, homeTeam, awayTeam, matchDate);

				matchesList.add(match);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return matchesList;
	}
	
	/***********************************
	 * UPDATE
	 ***********************************/

//	public void updateMatch (Match match) {
//		try {
//			String sql = "UPDATE matches SET  teamname=" + match.getHomeTeam() + ", awayteam=" + match.getAwayTeam()
//					+ ", winningteam=" + match.getWinningTeam() + ", matchdate=" + match.getMatchDate() + " WHERE id="
//							+ match.getMatchId();
//
//			Statement statement = connection.createStatement();
//
//			if (statement.executeUpdate(sql) == 0)
//				System.out.println("No matches to be updated!");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
	
	/***********************************
	 * DELETE
	 ***********************************/

	public void deleteMatch (Match match) {
		try {
			String sql = "DELETE FROM matches WHERE id=" + match.getMatchId();

			Statement statement = connection.createStatement();

			if (statement.executeUpdate(sql) == 0)
				System.out.println("No matches to be deleted!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
