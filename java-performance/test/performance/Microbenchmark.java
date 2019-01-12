package performance;

public abstract class Microbenchmark {
	
	protected abstract void innerTest();
	
	private long elapsedTime = 0;

	public long getElapsedTime() {
		return elapsedTime;
	}

	private void setElapsedTime(long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	private void doTest(boolean isWarmup) {
		
		long then = System.currentTimeMillis();
		
		innerTest();
		
		if (!isWarmup) {
			long now = System.currentTimeMillis();
			setElapsedTime(now - then);
		}
	}
	
	public void test() {
		doTest(true);
		doTest(false);
	}
}
