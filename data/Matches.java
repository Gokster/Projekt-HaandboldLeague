package data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entities.Team;
import entities.Goal;
import entities.Match;
import entities.Suspension;

public class Matches {
	private Connection connection;
	private Teams teams = new Teams(connection);
	private Goals goals = new Goals(connection);
	private Suspensions suspensions = new Suspensions(connection);
	
	public Matches(Connection connection) {
		this.connection = connection;
	}

	public void createMatch (Match match) {
		try {
			String sql = "INSERT INTO matches VALUES (" + match.getHomeTeam() + ", " + match.getAwayTeam() + ", " + match.getMatchDate() + ")";

			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);

			ResultSet resultSet = statement.executeQuery("SELECT SCOPE_IDENTITY()");
			resultSet.next();
			match.setMatchId(resultSet.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Match readMatchById (int id) {
		try {
			String sql = "SELECT * FROM matches WHERE id=" + id;

			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(sql);

			if (resultSet.next()) {
				Team homeTeam = teams.readTeamById(resultSet.getInt("hometeam"));
				Team awayTeam = teams.readTeamById(resultSet.getInt("awayteam"));
				Date matchDate = resultSet.getDate("matchdate");
				ArrayList<Goal> goalList = goals.getAllGoals(id);
				ArrayList<Suspension> suspensionList = suspensions.getAllSuspensions(id);

				return new Match(id, homeTeam, awayTeam, matchDate, goalList, suspensionList);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void updateMatch (Match match) {
		try {
			String sql = "UPDATE matches SET  teamname=" + match.getHomeTeam() + ", awayteam=" + match.getAwayTeam()
					+ ", winningteam=" + match.getWinningTeam() + ", matchdate=" + match.getMatchDate() + " WHERE id="
							+ match.getMatchId();

			Statement statement = connection.createStatement();

			if (statement.executeUpdate(sql) == 0)
				System.out.println("No matches to be updated!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

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
	public ArrayList<Match> getAllMatches() {
		ArrayList<Match> matchesList = new ArrayList<>();

		try {
			String sql = "SELECT * FROM matches";

			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				Team homeTeam = teams.readTeamById(resultSet.getInt("hometeam"));
				Team awayTeam = teams.readTeamById(resultSet.getInt("awayteam"));
				Date matchDate = resultSet.getDate("matchdate");
				ArrayList<Goal> goalList = goals.getAllGoals(id);
				ArrayList<Suspension> suspensionList = suspensions.getAllSuspensions(id);
				
				Match match = new Match(id, homeTeam, awayTeam, matchDate, goalList, suspensionList);

				matchesList.add(match);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return matchesList;
	}
}
