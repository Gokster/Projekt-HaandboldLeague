package logic;

public class StopWatch {

	private long startTime;
	private long stopTime;
	private boolean running = false;

	public void start() {
		this.startTime = System.currentTimeMillis();
		this.running = true;
	}

	public void stop() {
		this.stopTime = System.currentTimeMillis();
		this.running = false;
	}

	public long getElapsedMillis() {
		long elapsed;

		if (running) {
			elapsed = (System.currentTimeMillis() - startTime);
		} else {
			elapsed = (stopTime - startTime);
		}

		return elapsed;
	}

	public long getElapsedSecs() {
		long elapsed;

		if (running) {
			elapsed = ((System.currentTimeMillis() - startTime) / 1000);
		} else {
			elapsed = ((stopTime - startTime) / 1000);
		}
		
		return elapsed;
	}
	
	public long getElapsedMins() {
		long elapsed;

		if (running) {
			elapsed = ((System.currentTimeMillis() - startTime) / 1000 / 60);
		} else {
			elapsed = ((stopTime - startTime) / 1000 / 60);
		}

		return elapsed;
	}
}