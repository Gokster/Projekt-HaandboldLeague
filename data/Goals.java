package data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

import entities.Goal;

public class Goals {
	private Connection connection;

	public void createGoal(Goal goal) {
		try {
			String sql = "INSERT INTO goals VALUES (" + goal.getScoringTeam() + ", "
					+ goal.getMatchTime() + ", " + goal.getMatchId() + ")";

			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);

			ResultSet resultSet = statement.executeQuery("SELECT SCOPE_IDENTITY()");
			resultSet.next();
			goal.setGoalId(resultSet.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Goal readGoalById (int id) {
		try {
			String sql = "SELECT * FROM goal WHERE id=" + id;
			System.out.println(sql);

			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(sql);

			if (resultSet.next()) {
				int scoringTeam = resultSet.getInt("scoringteam");
				Time matchTime = resultSet.getTime("matchtime");
				int matchId = resultSet.getInt("matchId");

				return new Goal (id, scoringTeam, matchTime, matchId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void updateGoal(Goal goal) {
		try {
			String sql = "UPDATE matches SET scoringteam=" + goal.getScoringTeam() + ", matchtime=" + goal.getMatchTime()
					+ ", matchid=" + goal.getMatchId();

			Statement statement = connection.createStatement();

			if (statement.executeUpdate(sql) == 0)
				System.out.println("No goals to update!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteGoal(Goal goal) {
		try {
			String sql = "DELETE FROM goal WHERE id=" + goal.getGoalId();

			Statement statement = connection.createStatement();

			if (statement.executeUpdate(sql) == 0)
				System.out.println("No goals to delete!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Goal> getAllGoals(int scoringTeam, int matchId) {
		ArrayList<Goal> goals = new ArrayList<>();

		try {
			String sql = "SELECT * FROM goals";

			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(sql);

			if (resultSet.next()) {
				int id = resultSet.getInt("id");
				scoringTeam = resultSet.getInt("scoringteam");
				Time matchTime = resultSet.getTime("matchtime");
				matchId = resultSet.getInt("matchId");

				Goal goal = new Goal (id, scoringTeam, matchTime, matchId);

				goals.add(goal);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return goals;
	}
}
