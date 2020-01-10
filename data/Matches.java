package data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import entities.Team;
import entities.Match;

public class Matches {
	private Connection connection;
	private Teams teams = new Teams();

	public void createMatch (Match match) {
		try {
			String sql = "INSERT INTO matches VALUES (" + match.getHomeTeam() + ", " + match.getAwayTeam() + ", "
					+ match.getWinningTeam() + ", " + match.getMatchDate() + ")";

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
			System.out.println(sql);

			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(sql);

			if (resultSet.next()) {
				Team homeTeam = teams.readTeamById(resultSet.getInt("hometeam"));
				Team awayTeam = teams.readTeamById(resultSet.getInt("awayteam"));
				Team winningTeam = teams.readTeamById(resultSet.getInt("winningteam"));
				Date matchDate = resultSet.getDate("matchdate");

				return new Match(id, homeTeam, awayTeam, winningTeam, matchDate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void updateMatch (Match match) {
		try {
			String sql = "UPDATE matches SET  teamname=" + match.getHomeTeam() + ", awayteam=" + match.getAwayTeam()
					+ ", winningteam=" + match.getWinningTeam() + ", matchdate=" + match.getMatchDate();

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
}
