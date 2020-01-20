package logic;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;

import data.DatabaseController;
import entities.Goal;
import entities.Match;
import entities.Suspension;
import entities.Team;
import presentation.SpecificMatchHistoryTable;

public class MatchReport2 {
	private int homeTeamScore = 0;
	private int awayTeamScore = 0;

	public ArrayList<SpecificMatchHistoryTable> matchReport(Match match) {
		ArrayList<SpecificMatchHistoryTable> eventList = new ArrayList<>();
		
		String homeTeam = match.getHomeTeam().getTeamName();
		String awayTeam = match.getAwayTeam().getTeamName();
		
		for (int i = 0; i < match.getGoalList().size(); i++) {
			if (match.getGoalList().get(i).getScoringTeam().getTeamName() == homeTeam) {
				String goalH = "Goal";
				String goalA = "";
				SpecificMatchHistoryTable event = new SpecificMatchHistoryTable(goalH,
						Long.toString(match.getGoalList().get(i).getMatchTime().getSeconds()), goalA);
				eventList.add(event);
				
				homeTeamScore++;
			} else if (match.getGoalList().get(i).getScoringTeam().getTeamName() == awayTeam) {
				String goalA = "Goal";
				String goalH = "";
				SpecificMatchHistoryTable event = new SpecificMatchHistoryTable(goalH,
						Long.toString(match.getGoalList().get(i).getMatchTime().getSeconds()), goalA);
				eventList.add(event);
				
				awayTeamScore++;
			}
		}

		for (int i = 0; i < match.getSuspensionList().size(); i++) {
			if (match.getSuspensionList().get(i).getSuspensionTeam().getTeamName() == homeTeam) {
				String susH = "Suspension";
				String susA = "";
				SpecificMatchHistoryTable event = new SpecificMatchHistoryTable(susH,
						Long.toString(match.getSuspensionList().get(i).getMatchTime().getSeconds()), susA);
				eventList.add(event);
			} else if (match.getSuspensionList().get(i).getSuspensionTeam().getTeamName() == awayTeam) {
				String susA = "Suspension";
				String susH = "";
				SpecificMatchHistoryTable event = new SpecificMatchHistoryTable(susH,
						Long.toString(match.getSuspensionList().get(i).getMatchTime().getSeconds()), susA);
				eventList.add(event);
			}
		}
		
		Collections.sort(eventList, new TimestampCompare());
		return eventList;
	}

	public String matchScore() {
		String matchScore = Integer.toString(homeTeamScore) + " - " + Integer.toString(awayTeamScore);
		
		return matchScore;
	}
}
