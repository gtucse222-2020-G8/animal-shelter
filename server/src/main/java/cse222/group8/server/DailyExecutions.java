package cse222.group8.server;

/**
 * The type Daily executions.
 */
public class DailyExecutions implements Runnable {

	/**
	 * The System.
	 */
	ShelterSystem system;

	/**
	 * Instantiates a new Daily executions.
	 *
	 * @param system the system
	 */
	public DailyExecutions(ShelterSystem system) {
		this.system = system;
	}

	/**
	 * Reset daily tasks.
	 */
	public void resetDailyTasks() {
		int shelterSize = system.getCitiesBST().getData().getTowns().getData().getShelters().size();
		for(int i=0; i< shelterSize; ++i) {
			//system.getCitiesBST().getData().getTowns().getData().getShelters().get(i).getTasks().
		}
	}

	/**
	 * Drop expired adoption requests.
	 */
	public void dropExpiredAdoptionRequests() {
		
	}

	@Override
	public void run() {

	}
}
