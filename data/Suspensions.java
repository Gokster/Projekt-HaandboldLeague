package data;

import java.sql.*;
import java.util.ArrayList;
import entities.MatchTime;
import entities.Suspension;
import entities.Team;

public class Suspensions {
	private Connection connection;
	
	public Suspensions(Connection connection) {
		this.connection = connection;
	}
	
	/***********************************
	 * CREATE
	 ***********************************/

	public void createSuspension(Suspension suspension) {
		try {
			String sql = "INSERT INTO suspensions VALUES (" + suspension.getSuspensionTeam().getTeamId() 
					+ ", " + suspension.getMatchTime().getSeconds()
					+ ", " + suspension.getMatchId() + ")";

			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/***********************************
	 * READ
	 ***********************************/
	
	public ArrayList<Suspension> getAllSuspensions(ArrayList<Team> teamList) {
		ArrayList<Suspension> suspensionsList = new ArrayList<>();

		try {
			String sql = "SELECT * FROM suspensions";

			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(sql);

			
			while (resultSet.next()) {
				Team suspensionTeam = null;
				for(Team team : teamList) {
					if(resultSet.getInt("suspensionteam") == team.getTeamId()) {
						suspensionTeam = team;
					}
				}
				
				MatchTime matchTime = new MatchTime(resultSet.getInt("matchtime"));
				int matchId = resultSet.getInt("matchid");
				
				Suspension suspension = new Suspension(suspensionTeam, matchTime, matchId);

				suspensionsList.add(suspension);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return suspensionsList;
	}
	
	public ArrayList<Suspension> getAllSuspensions(int matchId, ArrayList<Team> teamList) {
		ArrayList<Suspension> suspensionsList = new ArrayList<>();

		try {
			String sql = "SELECT * FROM suspensions WHERE matchid=" + matchId;

			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				Team suspensionTeam = null;
				for(Team team : teamList) {
					if(resultSet.getInt("suspensionteam") == team.getTeamId()) {
						suspensionTeam = team;
					}
				}
				
				MatchTime matchTime = new MatchTime(resultSet.getInt("matchtime"));
				
				Suspension suspension = new Suspension(suspensionTeam, matchTime, matchId);

				suspensionsList.add(suspension);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return suspensionsList;
	}
}