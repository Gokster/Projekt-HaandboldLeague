package logic;

import java.util.ArrayList;
import java.util.Collections;

import entities.MatchHistoryTable;

public class MatchReport {
	private int homeTeamScore = 0;
	private int awayTeamScore = 0;

	public ArrayList<MatchHistoryTable> createMatchReport(Match match) {
		ArrayList<MatchHistoryTable> eventList = new ArrayList<>();

		String homeTeam = match.getHomeTeam().getTeamName();
		String awayTeam = match.getAwayTeam().getTeamName();

		for (int i = 0; i < match.getGoalList().size(); i++) {
			if (match.getGoalList().get(i).getScoringTeam().getTeamName() == homeTeam) {
				String goalH = "Goal";
				String goalA = "";
				MatchHistoryTable event = new MatchHistoryTable(goalH, matchTimeGoal(match, i), goalA);
				eventList.add(event);

				homeTeamScore++;
			} else if (match.getGoalList().get(i).getScoringTeam().getTeamName() == awayTeam) {
				String goalA = "Goal";
				String goalH = "";
				MatchHistoryTable event = new MatchHistoryTable(goalH, matchTimeGoal(match, i), goalA);
				eventList.add(event);

				awayTeamScore++;
			}
		}

		for (int i = 0; i < match.getSuspensionList().size(); i++) {
			if (match.getSuspensionList().get(i).getSuspensionTeam().getTeamName() == homeTeam) {
				String susH = "Suspension";
				String susA = "";
				MatchHistoryTable event = new MatchHistoryTable(susH, matchTimeSuspension(match, i),
						susA);
				eventList.add(event);
			} else if (match.getSuspensionList().get(i).getSuspensionTeam().getTeamName() == awayTeam) {
				String susA = "Suspension";
				String susH = "";
				MatchHistoryTable event = new MatchHistoryTable(susH, matchTimeSuspension(match, i),
						susA);
				eventList.add(event);
			}
		}

		Collections.sort(eventList, new TimestampCompare());
		return eventList;
	}
	
	/***********************************
	 * STRING GENERATORS
	 ***********************************/

	public String matchScore() {
		return Integer.toString(homeTeamScore) + " - " + Integer.toString(awayTeamScore);
	}

	private String matchTimeGoal(Match match, int i) {
		long time = match.getGoalList().get(i).getMatchTime().getSeconds();
		return matchTimeString(time);
	}

	private String matchTimeSuspension(Match match, int i) {
		long time = match.getSuspensionList().get(i).getMatchTime().getSeconds();
		return matchTimeString(time);
	}

	private String matchTimeString(long time) {
		String timeStamp;

		if (time % 60 < 10) {
			timeStamp = Long.toString(time / 60) + ":0" + Long.toString(time % 60);
		} else {
			timeStamp = Long.toString(time / 60) + ":" + Long.toString(time % 60);
		}

		return timeStamp;
	}
}
