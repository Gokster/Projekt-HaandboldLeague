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
						Integer.toString(match.getGoalList().get(i).getTimstam()), goalA);
				eventList.add(event);
				
				homeTeamScore++;
			} else if (match.getGoalList().get(i).getScoringTeam().getTeamName() == awayTeam) {
				String goalA = "Goal";
				String goalH = "";
				SpecificMatchHistoryTable event = new SpecificMatchHistoryTable(goalH,
						Integer.toString(match.getGoalList().get(i).getTimstam()), goalA);
				eventList.add(event);
				
				awayTeamScore++;
			}
		}

		for (int i = 0; i < match.getSuspensionList().size(); i++) {
			if (match.getSuspensionList().get(i).getSuspensionTeam().getTeamName() == homeTeam) {
				String susH = "Suspension";
				String susA = "";
				SpecificMatchHistoryTable event = new SpecificMatchHistoryTable(susH,
						Integer.toString(match.getSuspensionList().get(i).getTimstam()), susA);
				eventList.add(event);
			} else if (match.getSuspensionList().get(i).getSuspensionTeam().getTeamName() == awayTeam) {
				String susA = "Suspension";
				String susH = "";
				SpecificMatchHistoryTable event = new SpecificMatchHistoryTable(susH,
						Integer.toString(match.getSuspensionList().get(i).getTimstam()), susA);
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
	
	public ArrayList<SpecificMatchHistoryTable> matchReportTest() {
		//***TEST***

				Team team1 = new Team(1, "Ikast FC", 26);
				Team team2 = new Team(2, "Herning FC", 23);

				ArrayList<Goal> goalsListTest = new ArrayList<>();
				Goal goalH1 = new Goal(1, team1, 12, 1);
				Goal goalH2 = new Goal(1, team1, 20, 1);
				Goal goalH3 = new Goal(1, team1, 30, 1);
				Goal goalH4 = new Goal(1, team1, 40, 1);

				Goal goalA1 = new Goal(1, team2, 15, 1);
				Goal goalA2 = new Goal(1, team2, 18, 1);
				Goal goalA3 = new Goal(1, team2, 25, 1);
				Goal goalA4 = new Goal(1, team2, 39, 1);

				goalsListTest.add(goalH1);
				goalsListTest.add(goalH2);
				goalsListTest.add(goalH3);
				goalsListTest.add(goalH4);

				goalsListTest.add(goalA1);
				goalsListTest.add(goalA2);
				goalsListTest.add(goalA3);
				goalsListTest.add(goalA4);

				ArrayList<Suspension> suspensionsListTest = new ArrayList<>();
				Suspension susH1 = new Suspension(1, team1, 7, 1);
				Suspension susH2 = new Suspension(1, team1, 19, 1);
				Suspension susH3 = new Suspension(1, team1, 31, 1);
				Suspension susH4 = new Suspension(1, team1, 42, 1);

				Suspension susA1 = new Suspension(1, team2, 23, 1);
				Suspension susA2 = new Suspension(1, team2, 38, 1);
				Suspension susA3 = new Suspension(1, team2, 49, 1);
				Suspension susA4 = new Suspension(1, team2, 54, 1);

				suspensionsListTest.add(susH1);
				suspensionsListTest.add(susH2);
				suspensionsListTest.add(susH3);
				suspensionsListTest.add(susH4);

				suspensionsListTest.add(susA1);
				suspensionsListTest.add(susA2);
				suspensionsListTest.add(susA3);
				suspensionsListTest.add(susA4);

				Date dateOfMatch = new Date(120, 05, 05);

				Match matchTest = new Match(1, team1, team2, dateOfMatch, goalsListTest, suspensionsListTest);

				ArrayList<SpecificMatchHistoryTable> eventListTest = new ArrayList<>();

				String homeTeamTest = matchTest.getHomeTeam().getTeamName();
				String awayTeamTest = matchTest.getAwayTeam().getTeamName();
				
				for (int i = 0; i < goalsListTest.size(); i++) {
					if (goalsListTest.get(i).getScoringTeam().getTeamName() == homeTeamTest) {
						String goalH = "Goal";
						String goalA = "";
						SpecificMatchHistoryTable event = new SpecificMatchHistoryTable(goalH,
								Integer.toString(goalsListTest.get(i).getTimstam()), goalA);
						eventListTest.add(event);
						
						homeTeamScore++;
					} else if (goalsListTest.get(i).getScoringTeam().getTeamName() == awayTeamTest) {
						String goalA = "Goal";
						String goalH = "";
						SpecificMatchHistoryTable event = new SpecificMatchHistoryTable(goalH,
								Integer.toString(goalsListTest.get(i).getTimstam()), goalA);
						eventListTest.add(event);
						
						awayTeamScore++;
					}
				}

				for (int i = 0; i < suspensionsListTest.size(); i++) {
					if (suspensionsListTest.get(i).getSuspensionTeam().getTeamName() == homeTeamTest) {
						String susH = "Suspension";
						String susA = "";
						SpecificMatchHistoryTable event = new SpecificMatchHistoryTable(susH,
								Integer.toString(suspensionsListTest.get(i).getTimstam()), susA);
						eventListTest.add(event);
					} else if (suspensionsListTest.get(i).getSuspensionTeam().getTeamName() == awayTeamTest) {
						String susA = "Suspension";
						String susH = "";
						SpecificMatchHistoryTable event = new SpecificMatchHistoryTable(susH,
								Integer.toString(suspensionsListTest.get(i).getTimstam()), susA);
						eventListTest.add(event);
					}
				}
				//***TEST***
				Collections.sort(eventListTest, new TimestampCompare());
				return eventListTest;
	}
	public String matchScoreTest() {
		String matchScore = Integer.toString(homeTeamScore) + " - " + Integer.toString(awayTeamScore);
		
		return matchScore;
	}
}
