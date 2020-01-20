package entities;

public class MatchTime {
	private long millis = 0;
	private long seconds = 0;
	private long minutes = 0;
	
	private long startTime;
	private long stopTime;
	private boolean running = false;
	
	public MatchTime() {
		
	} 
	
	public MatchTime(int timeStampSeconds) {
		this.seconds = timeStampSeconds;
	}
	
	public MatchTime(MatchTime matchTime) {
		this.millis = matchTime.getMillis();
		this.seconds = matchTime.getSeconds();
		this.minutes = matchTime.getMinutes();
	}
	
	public void startMatchTimer() {
		this.startTime = System.currentTimeMillis();
		this.running = true;
	}
	
	public void stopMatchTimer() {
		this.stopTime = System.currentTimeMillis();
		this.running = false;
	}
	
	public void getMatchTime() {
		millis = getMillis() % 1000;
		seconds = getSeconds() % 60;
		minutes = getMinutes();
	}

	public long getMillis() {
		long elapsed;

		if (running) {
			elapsed = (System.currentTimeMillis() - startTime);
		} else {
			elapsed = (stopTime - startTime);
		}
		return elapsed;
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

	public long getMinutes() {
		long elapsed;

		if (running) {
			elapsed = ((System.currentTimeMillis() - startTime) / 1000 / 60);
		} else {
			elapsed = ((stopTime - startTime) / 1000 / 60);
		}
		return elapsed;
	}
	
	public long getSeconds() {
		return seconds;
	}
}

