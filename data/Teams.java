package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entities.Team;

public class Teams {
	private Connection connection;

	public void createTeam(Team team) {
		try {
			String sql = "INSERT INTO teams VALUES ('" + team.getTeamName() + "', " + team.getTeamPoints() + ")";

			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);

			// auto-generated key => id
			ResultSet resultSet = statement.executeQuery("SELECT SCOPE_IDENTITY()");
			resultSet.next();
			team.setTeamId(resultSet.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Team readTeamByID(int id) {
		try {
			String sql = "SELECT * FROM teams WHERE id=" + id;
			System.out.println(sql);

			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(sql);

			if (resultSet.next()) {
				String teamName = resultSet.getString("teamname");
				int teamPoints = resultSet.getInt("teampoints");

				return new Team(id, teamName, teamPoints);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void updateTeam(Team team) {
		try {
			String sql = "UPDATE teams SET teamname='" + team.getTeamName() + "', teampoints=" + team.getTeamPoints();

			Statement statement = connection.createStatement();

			if (statement.executeUpdate(sql) == 0)
				System.out.println("Ingen teams at opdatere!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteTeam(Team team) {
		try {
			String sql = "DELETE FROM teams WHERE id=" + team.getTeamId();

			Statement statement = connection.createStatement();

			if (statement.executeUpdate(sql) == 0)
				System.out.println("Ingen teams at slette!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
