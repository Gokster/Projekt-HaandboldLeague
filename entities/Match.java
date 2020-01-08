package entities;

import java.util.Date;

public class Match {
	private int matchId;
	private int homeTeam;
	private int awayTeam;
	private int winningTeam;
	private Date matchDate;
	
	public Match(int matchId, int homeTeam, int awayTeam, int winningTeam, Date matchDate) {
		this.matchId = matchId;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.winningTeam = winningTeam;
		this.matchDate = matchDate;

	}

	public int getMatchId() {
		return matchId;
	}

	public int getHomeTeam() {
		return homeTeam;
	}

	public int getAwayTeam() {
		return awayTeam;
	}

	public int getWinningTeam() {
		return winningTeam;
	}

	public Date getMatchDate() {
		return matchDate;
	}
	
	
}