package entities;

import java.util.Date;

public class Match {
	private int matchID;
	private int homeTeam;
	private int awayTeam;
	private int winningTeam;
	private Date matchDate;
	
	public Match(int matchID, int homeTeam, int awayTeam, int winningTeam, Date matchDate) {
		this.matchID = matchID;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.winningTeam = winningTeam;
		this.matchDate = matchDate;
	}
	Goal goalArr[] = new Goal[1000];

	private int countGoal(Goal[] goalArr) {
		int firstTeamScore = 0;
		int secondTeamScore = 0;
		
		for(Goal goal: goalArr ) {
			if(goal.getScoringTeam() == homeTeam) {
				firstTeamScore++;
			}
			else
				secondTeamScore++;
		}
		
		if(firstTeamScore > secondTeamScore)
			winningTeam = firstTeamScore;
		
		if(firstTeamScore < secondTeamScore)
			winningTeam = secondTeamScore;
		
		return winningTeam;
		
	}
}
