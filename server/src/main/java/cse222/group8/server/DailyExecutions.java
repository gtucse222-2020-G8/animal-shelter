package cse222.group8.server;

public class DailyExecutions {
	
	ShelterSystem system;
	
	public DailyExecutions(ShelterSystem system) {
		this.system = system;
	}
	
	public void resetDailyTasks() {
		int shelterSize = system.getCitiesBST().getData().getTowns().getData().getShelters().size();
		for(int i=0; i< shelterSize; ++i) {
			//system.getCitiesBST().getData().getTowns().getData().getShelters().get(i).getTasks().
		}
	}
	
	public void dropExpiredAdoptionRequests() {
		
	}

}
