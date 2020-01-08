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
}
