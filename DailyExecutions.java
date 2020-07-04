package cse222.group8.server;

import java.util.Date;

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
		for(City city : system.getCitiesBST()){
			for(Town town : city.getTowns()){
				for(Shelter shelter : town.getShelters()){
					for(Task task : shelter.getTasks()){
						task.setStatus(false);
					}
				}
			}
		}
	}

	/**
	 * Drop expired adoption requests.
	 */
	public void dropExpiredAdoptionRequests() {
		for(User user : system.getUsers()){
			for(AdoptionRequest request : user.getRequests()){
				if(request.getExpirationDate().compareTo(new Date())<0){
					system.removeAdoptionRequest(request);
				}
			}
		}
	}

	@Override
	public void run() {
		resetDailyTasks();
		dropExpiredAdoptionRequests();
	}
}
