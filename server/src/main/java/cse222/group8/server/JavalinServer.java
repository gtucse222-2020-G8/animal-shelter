package cse222.group8.server;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import cse222.group8.server.DataStructures.BinarySearchTree;
import cse222.group8.server.clientModels.GeneralShelterData;
import cse222.group8.server.clientModels.RegisterShelterData;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.plugin.json.JavalinJackson;

import java.util.LinkedList;
import java.util.List;

public class JavalinServer implements Runnable {

    private Javalin app;
    private ShelterSystem system;
    Algorithm jwtAlgorithm;
    JWTVerifier verifier;

    public JavalinServer(ShelterSystem system){
        this.system = system;
        jwtAlgorithm = Algorithm.HMAC256("adgsdgdfsgasdgas");
        verifier = JWT.require(jwtAlgorithm).build();
    }
    public String createShelterToken(String cityName, String townName, String shelterName) {
        return JWT.create().withClaim("City", cityName)
                           .withClaim("Town", townName)
                           .withClaim("Shelter", shelterName)
                           .withClaim("IsShelter", true)
                  .sign(jwtAlgorithm);
    }
    private boolean hasJwt(Context ctx){
        if(ctx.header("Authorization")!=null && ctx.header("Authorization")!=""){
            return true;
        }
        return false;
    }
    private DecodedJWT verifyBearer(String str){
        String token = str.substring(str.indexOf("rer "));
        try {
            DecodedJWT jwt = verifier.verify(token);
            return jwt;
        } catch (JWTVerificationException exception){
            System.out.println(exception);
            return null;
        }
    }

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
        app = Javalin.create(javalinConfig -> javalinConfig.defaultContentType = "application/json");
        initRoutes();
        app.start(8080);
	}

	private void getCities(Context ctx){
        //TODO
    }

    private void getTowns(Context ctx){
        String cityName = ctx.header("City");
        City city = system.getCity(cityName);
        BinarySearchTree<Town> towns = city.getTowns();
        List<String> townNames = new LinkedList<String>();
        for(Town town : towns){
            townNames.add(town.getName());
        }
        ctx.json((Town[])townNames.toArray());
    }
    private Shelter getShelterFromJWT(DecodedJWT jwt){
        String cityName = jwt.getClaim("City").asString();
        String townName = jwt.getClaim("Town").asString();
        String shelterName = jwt.getClaim("Shelter").asString();
        return system.getCity(cityName).getTown(townName).getShelter(shelterName);
    }
    private void getShelters(Context ctx){
        String cityName = ctx.header("City");
        String townName = ctx.header("Town");
        City city = system.getCity(cityName);
        Town town = city.getTown(townName);
        List<Shelter> shelters = town.getShelters();
        List<String> shelterNames = new LinkedList<String>();
        for(Shelter shelter : shelters){
            shelterNames.add(shelter.getName());
        }
    }
    private void getGeneralShelterData(Context ctx){
        if(hasJwt(ctx)){
            DecodedJWT jwt = verifyBearer(ctx.header("Authorization"));
            if(jwt!=null){
                if(jwt.getClaim("IsShelter").asBoolean()) {
                    Shelter shelter = getShelterFromJWT(jwt);
                    GeneralShelterData generalShelterData = new GeneralShelterData();
                    generalShelterData.adoptionRequestsCount = shelter.getAdoptionRequests().size();
                    generalShelterData.catCapacity = shelter.getCatCapacity();
                    generalShelterData.dogCapacity = shelter.getDogCapacity();
                    generalShelterData.catCount = shelter.getCatSize();
                    generalShelterData.dogCount = shelter.getDogSize();
                    ctx.status(200);
                    ctx.json(generalShelterData);
                }
                else{
                    ctx.status(403);
                    ctx.json("Jwt is not for shelter");
                }
            }
            else{
                ctx.status(403);
                ctx.json("Invalid jwt");
            }
        }
        else {
            ctx.status(403);
            ctx.json("Authorization header not found");
        }
    }
    private void createShelter(Context ctx){
        if(hasJwt(ctx)) {
            DecodedJWT jwt = verifyBearer(ctx.header("Authorization"));
            if (jwt != null) {
                if (jwt.getClaim("IsShelter").asBoolean()) {
                    ObjectMapper mapper = JavalinJackson.getObjectMapper();
                    RegisterShelterData data;
                    try {
                        data = mapper.readValue(ctx.body(), RegisterShelterData.class);
                        Shelter shelter = new Shelter()
                        system.addNewShleterRequest(new ShelterRequest())
                    } catch (Exception e) {
                        ctx.status(432);
                        return;
                    }
                }
                else{
                    ctx.status(403);
                    ctx.json("Jwt is not for shelter");
                }
            }
            else{
                ctx.status(403);
                ctx.json("Invalid jwt");
            }
        }
        else {
            ctx.status(403);
            ctx.json("Authorization header not found");
        }
    }
    private void getShelterRegisterationStatus(Context ctx){}
    private void shelterLogin(Context ctx){}
    private void getAnimal(Context ctx){}
    private void updateAnimalData(Context ctx){}
    private void updateAnimalPicture(Context ctx){}
    private void getShelterCats(Context ctx){}
    private void getShelterDogs(Context ctx){}
    private void addNewCat(Context ctx){}
    private void addNewDog(Context ctx){}
    private void shelterUpdatePassword(Context ctx){}
    private void shelterUpdateCapacity(Context ctx){}
    private void shelterUpdateName(Context ctx){}
    private void getShelterAdoptionRequest(Context ctx){}
    private void approveAdoptionRequest(Context ctx){}
    private void getDailyTasks(Context ctx){}
    private void createDailyTask(Context ctx){}
    private void deleteDailyTask(Context ctx){}
    private void updateDailyTask(Context ctx){}
    private void approveDailyTask(Context ctx){}
}
