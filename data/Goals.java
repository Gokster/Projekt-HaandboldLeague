package data;

import java.sql.*;
import java.util.ArrayList;
import entities.Goal;
import entities.MatchTime;
import entities.Team;

public class Goals {
	private Connection connection;

	public Goals(Connection connection) {
		this.connection = connection;
	}
	
	/***********************************
	 * CREATE
	 ***********************************/
	
	public void createGoal(Goal goal) {
		try {
			String sql = "INSERT INTO goals VALUES (" + goal.getScoringTeam().getTeamId() 
					+ ", " + goal.getMatchTime().getSeconds() 
					+ ", " + goal.getMatchId() + ")";

			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);

			ResultSet resultSet = statement.executeQuery("SELECT SCOPE_IDENTITY()");
			resultSet.next();
			goal.setGoalId(resultSet.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/***********************************
	 * READ
	 ***********************************/
	
	public ArrayList<Goal> getAllGoals(ArrayList<Team> teamList) {
		ArrayList<Goal> goalsList = new ArrayList<>();

		try {
			String sql = "SELECT * FROM goals";

			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				Team scoringTeam = null;
				for (int i = 0; i < teamList.size(); i++) {
					if (teamList.get(i).getTeamId() == resultSet.getInt("scoringteam")) {
						scoringTeam = teamList.get(i);
						break;
					}
				}

				MatchTime matchTime = new MatchTime(resultSet.getInt("matchtime"));
				int matchId = resultSet.getInt("matchid");

				Goal goal = new Goal(id, scoringTeam, matchTime, matchId);

				goalsList.add(goal);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return goalsList;
	}

	public ArrayList<Goal> getAllGoals(int matchId, ArrayList<Team> teamList) {
		ArrayList<Goal> goalsList = new ArrayList<>();

		try {
			String sql = "SELECT * FROM goals WHERE matchid=" + matchId;

			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				Team scoringTeam = null;
				for (int i = 0; i < teamList.size(); i++) {
					if (teamList.get(i).getTeamId() == resultSet.getInt("scoringteam")) {
						scoringTeam = teamList.get(i);
						break;
					}
				}
			
				MatchTime matchTime = new MatchTime(resultSet.getInt("matchtime"));

				Goal goal = new Goal(id, scoringTeam, matchTime, matchId);

				goalsList.add(goal);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return goalsList;
	}
	
	/***********************************
	 * UPDATE
	 ***********************************/

//	public void updateGoal(Goal goal) {
//		try {
//			String sql = "UPDATE matches SET scoringteam=" + goal.getScoringTeam() + ", matchtime="
//					+ goal.getMatchTime() + ", matchid=" + goal.getMatchId() + " WHERE id=" + goal.getGoalId();
//
//			Statement statement = connection.createStatement();
//
//			if (statement.executeUpdate(sql) == 0)
//				System.out.println("No goals to update!");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
	
	/***********************************
	 * DELETE
	 ***********************************/

//	public void deleteGoal(Goal goal) {
//		try {
//			String sql = "DELETE FROM goal WHERE id=" + goal.getGoalId();
//
//			Statement statement = connection.createStatement();
//
//			if (statement.executeUpdate(sql) == 0)
//				System.out.println("No goals to delete!");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
}
