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
	
	/***********************************
	 * READ
	 ***********************************/
	
	public ArrayList<Suspension> readAllSuspensions(ArrayList<Team> teamList) {
		ArrayList<Suspension> suspensionsList = new ArrayList<>();

		try {
			String sql = "SELECT * FROM suspensions";

			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(sql);

			
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				
				Team suspensionTeam = null;
				for(Team team : teamList) {
					if(resultSet.getInt("suspensionteam") == team.getTeamId()) {
						suspensionTeam = team;
					}
				}
				
				MatchTime matchTime = new MatchTime(resultSet.getInt("matchtime"));
				int matchId = resultSet.getInt("matchid");
				
				Suspension suspension = new Suspension(id, suspensionTeam, matchTime, matchId);

				suspensionsList.add(suspension);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return suspensionsList;
	}
	
	public ArrayList<Suspension> readAllSuspensions(int matchId, ArrayList<Team> teamList) {
		ArrayList<Suspension> suspensionsList = new ArrayList<>();

		try {
			String sql = "SELECT * FROM suspensions WHERE matchid=" + matchId;

			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				
				Team suspensionTeam = null;
				for(Team team : teamList) {
					if(resultSet.getInt("suspensionteam") == team.getTeamId()) {
						suspensionTeam = team;
					}
				}
				
				MatchTime matchTime = new MatchTime(resultSet.getInt("matchtime"));
				
				Suspension suspension = new Suspension(id, suspensionTeam, matchTime, matchId);

				suspensionsList.add(suspension);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return suspensionsList;
	}
	
	/***********************************
	 * UPDATE
	 ***********************************/

//	public void updateSuspension(Suspension suspension) {
//		try {
//			String sql = "UPDATE matches SET  suspensionteam=" + suspension.getSuspensionTeam() + ", matchtime="
//					+ suspension.getMatchTime() + ", matchid=" + suspension.getMatchId() + " WHERE id="
//					+ suspension.getSuspensionId();
//
//			Statement statement = connection.createStatement();
//
//			if (statement.executeUpdate(sql) == 0)
//				System.out.println("No suspensions to update!");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
	
	/***********************************
	 * DELETE
	 ***********************************/

//	public void deleteSuspension(Suspension suspension) {
//		try {
//			String sql = "DELETE FROM suspensions WHERE id=" + suspension.getSuspensionId();
//
//			Statement statement = connection.createStatement();
//
//			if (statement.executeUpdate(sql) == 0)
//				System.out.println("No suspensions to delete!");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
}