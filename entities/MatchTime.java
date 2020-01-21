package entities;

public class MatchTime {
	
	private long seconds = 0;
	private long startTime;
	private long stopTime;
	private boolean running = false;
	
	public MatchTime() {
		
	} 
	
	public MatchTime(int timeStampSeconds) {
		this.seconds = timeStampSeconds;
	}
	
	public MatchTime(MatchTime matchTime) {
		this.seconds = matchTime.getSeconds();
	}
	
	public void startMatchTimer() {
		this.startTime = System.currentTimeMillis();
		this.running = true;
	}
	
	public void stopMatchTimer() {
		this.stopTime = System.currentTimeMillis();
		this.running = false;
	}
	
	public long calcSeconds() {
		long elapsed;

		if (running) {
			elapsed = ((System.currentTimeMillis() - startTime) / 1000);
		} else {
			elapsed = ((stopTime - startTime) / 1000);
		}
		return elapsed;
	}
	
	/***********************************
	 * GETTERS
	 ***********************************/
	
	public void getMatchTime() {
		seconds = getSeconds() % 60;
	}
	
	public long getSeconds() {
		return seconds;
	}
}

