package data;

import java.sql.*;
import java.util.ArrayList;
import entities.Team;

public class Teams {
	private Connection connection;
	
	public Teams(Connection connection) {
		this.connection = connection;
	}
	
	/***********************************
	 * CREATE
	 ***********************************/

	public void createTeam (Team team) {
		try {
			String sql = "INSERT INTO teams VALUES ('" + team.getTeamName() + "', " + team.getTeamPoints() + ")";

			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);

			ResultSet resultSet = statement.executeQuery("SELECT SCOPE_IDENTITY()");
			resultSet.next();
			team.setTeamId(resultSet.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Can't connect");
		}
	}
	
	/***********************************
	 * READ
	 ***********************************/
	
	public ArrayList<Team> readAllTeams() {
		ArrayList<Team> teamsList = new ArrayList<>();

		try {
			String sql = "SELECT * FROM teams";

			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String teamName = resultSet.getString("teamname");
				int teamPoints = resultSet.getInt("teampoints");

				Team team = new Team(id, teamName, teamPoints);

				teamsList.add(team);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return teamsList;
	}
	
	/***********************************
	 * UPDATE
	 ***********************************/

	public void updateTeam (Team team) {
		try {
			String sql = "UPDATE teams SET teampoints=" + team.getTeamPoints() 
					+ " WHERE id=" + team.getTeamId();

			Statement statement = connection.createStatement();

			if (statement.executeUpdate(sql) == 0)
				System.out.println("No teams to update!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/***********************************
	 * DELETE
	 ***********************************/

	public void deleteTeam (String teamName) {
		try {
			String sql = "DELETE FROM teams WHERE teamname='" + teamName + "'";

			Statement statement = connection.createStatement();

			if (statement.executeUpdate(sql) == 0)
				System.out.println("No teams to be deleted!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
