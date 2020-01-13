package entities;

import logic.StopWatch;

public class MatchTime {
	private StopWatch watch = new StopWatch();
	private long millis = 0;
	private long seconds = 0;
	private long minutes = 0;
	
	public void startMatchTimer() {
		watch.start();
	}
	
	public void stopMatchTimer() {
		watch.stop();
	}
	
	public void getMatchTime() {
		millis = watch.getElapsedMillis() % 1000;
		seconds = watch.getElapsedSecs() % 60;
		minutes = watch.getElapsedMins();
	}

	public long getMillis() {
		return millis;
	}

	public long getSeconds() {
		return seconds;
	}

	public long getMinutes() {
		return minutes;
	}
	
	
}
