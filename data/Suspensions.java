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

public class Suspensions {
	private Connection connection;

	public void createSuspension (Suspension suspension) {
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

	public Suspension readSuspensionById (int id) {
		try {
			String sql = "SELECT * FROM suspensions WHERE id=" + id;
			System.out.println(sql);

			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(sql);

			if (resultSet.next()) {
				int suspensionTeam = resultSet.getInt("suspensionteam");
				int matchTime = resultSet.getInt("matchtime");
				int matchId = resultSet.getInt("matchId");

				return new Suspension(id, suspensionTeam, matchTime, matchId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void updateSuspension (Suspension suspension) {
		try {
			String sql = "UPDATE matches SET  suspensionteam=" + suspension.getSuspensionTeam() + ", matchtime=" + suspension.getMatchTime()
					+ ", matchid=" + suspension.getMatchId();

			Statement statement = connection.createStatement();

			if (statement.executeUpdate(sql) == 0)
				System.out.println("No suspensions to update!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteSuspension (Suspension suspension) {
		try {
			String sql = "DELETE FROM suspensions WHERE id=" + suspension.getSuspensionId();

			Statement statement = connection.createStatement();

			if (statement.executeUpdate(sql) == 0)
				System.out.println("No suspensions to delete!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Suspension> getAllSuspensions(int suspensionTeam, int matchId) {
		ArrayList<Suspension> suspensions = new ArrayList<>();

		try {
			String sql = "SELECT * FROM suspensions";

			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(sql);

			if (resultSet.next()) {
				int id = resultSet.getInt("id");
				suspensionTeam = resultSet.getInt("suspensionteam");
				int matchTime = resultSet.getInt("matchtime");
				matchId = resultSet.getInt("matchId");

				Suspension suspension = new Suspension (id, suspensionTeam, matchTime, matchId);

				suspensions.add(suspension);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return suspensions;
	}
}