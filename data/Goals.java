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
				Team scoringTeam = null;
				for (int i = 0; i < teamList.size(); i++) {
					if (teamList.get(i).getTeamId() == resultSet.getInt("scoringteam")) {
						scoringTeam = teamList.get(i);
						break;
					}
				}

				MatchTime matchTime = new MatchTime(resultSet.getInt("matchtime"));
				int matchId = resultSet.getInt("matchid");

				Goal goal = new Goal(scoringTeam, matchTime, matchId);

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
				Team scoringTeam = null;
				for (int i = 0; i < teamList.size(); i++) {
					if (teamList.get(i).getTeamId() == resultSet.getInt("scoringteam")) {
						scoringTeam = teamList.get(i);
						break;
					}
				}
			
				MatchTime matchTime = new MatchTime(resultSet.getInt("matchtime"));

				Goal goal = new Goal(scoringTeam, matchTime, matchId);

				goalsList.add(goal);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return goalsList;
	}
}