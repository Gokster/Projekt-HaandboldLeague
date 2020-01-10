package data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

import entities.Goal;
import entities.Suspension;
import entities.Team;

public class Suspensions {
	private Connection connection;
	private Teams teams = new Teams();

	public void createSuspension(Suspension suspension) {
		try {
			String sql = "INSERT INTO suspensions VALUES (" + suspension.getSuspensionTeam() + ", "
					+ suspension.getMatchTime() + ", " + suspension.getMatchId() + ")";

			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);

			ResultSet resultSet = statement.executeQuery("SELECT SCOPE_IDENTITY()");
			resultSet.next();
			suspension.setSuspensionId(resultSet.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Suspension readSuspensionById(int id) {
		try {
			String sql = "SELECT * FROM suspensions WHERE id=" + id;
			System.out.println(sql);

			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(sql);

			if (resultSet.next()) {
				Team suspensionTeam = teams.readTeamById(resultSet.getInt("suspensionteam"));
				int matchTime = resultSet.getInt("matchtime");
				int matchId = resultSet.getInt("matchId");

				return new Suspension(id, suspensionTeam, matchTime, matchId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void updateSuspension(Suspension suspension) {
		try {
			String sql = "UPDATE matches SET  suspensionteam=" + suspension.getSuspensionTeam() + ", matchtime="
					+ suspension.getMatchTime() + ", matchid=" + suspension.getMatchId() + " WHERE id="
					+ suspension.getSuspensionId();

			Statement statement = connection.createStatement();

			if (statement.executeUpdate(sql) == 0)
				System.out.println("No suspensions to update!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteSuspension(Suspension suspension) {
		try {
			String sql = "DELETE FROM suspensions WHERE id=" + suspension.getSuspensionId();

			Statement statement = connection.createStatement();

			if (statement.executeUpdate(sql) == 0)
				System.out.println("No suspensions to delete!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Suspension> getAllSuspensions(int matchId) {
		ArrayList<Suspension> suspensionsArr = new ArrayList<>();

		try {
			String sql = "SELECT * FROM suspensions WHERE matchid=" + matchId;

			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				Team suspensionTeam = teams.readTeamById(resultSet.getInt("suspensionteam"));
				int matchTime = resultSet.getInt("matchtime");

				Suspension suspension = new Suspension(id, suspensionTeam, matchTime, matchId);

				suspensionsArr.add(suspension);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return suspensionsArr;
	}
}