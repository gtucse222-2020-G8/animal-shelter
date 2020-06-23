package cse222.group8.server;

import io.javalin.Javalin;
import io.javalin.apibuilder.EndpointGroup;

public class JavalinServer implements Runnable {

    private Javalin app;

    public void initRoutes(){
        app.get("/cities",this::getCities);
        app.get("/cities/towns",this::getTowns);
        app.get("/cities/towns/shelters",this::getShelters);
        app.get("/shelters",this::getGeneralShelterData);
        app.post("/shelters",this::createShelter);
        app.get("/shelters/registeration",this::getShelterRegisterationStatus);
        app.post("/shelters/login",this::shelterLogin);
        app.get("/shelters/animals",this::getAnimal);
        app.post("/shelters/animals/update",this::updateAnimalData);
        app.post("/shelters/animals/update/picture",this::updateAnimalPicture);
        app.get("/shelters/animals/cats",this::getShelterCats);
        app.get("/shelters/animals/dogs",this::getShelterDogs);
        app.post("/shelters/animals/cats",this::addNewCat);
        app.post("/shelters/animals/dogs",this::addNewDog);
        app.post("/shelters/update/password",this::shelterUpdatePassword);
        app.post("/shelters/update/capacity",this::shelterUpdateCapacity);
        app.post("/shelters/update/name",this::shelterUpdateName);
        app.get("/shelters/adoption-requests",this::getShelterAdoptionRequest);
        app.post("/shelters/adoption-requests/approve",this::approveAdoptionRequest);
        app.get("/shelters/daily-tasks",this::getDailyTasks);
        app.post("/shelters/daily-tasks",this::createDailyTask);
        app.post("/shelters/daily-tasks/delete",this::deleteDailyTask);
        app.post("/shelters/daily-tasks/update",this::updateDailyTask);
        app.post("/shelters/daily-tasks/approve",this::approveDailyTask);
    }

	@Override
	public void run() {
        app = Javalin.create(javalinConfig -> {
            javalinConfig.defaultContentType = "application/json";
        });
        initRoutes();
        app.start(8080);
	}
}
