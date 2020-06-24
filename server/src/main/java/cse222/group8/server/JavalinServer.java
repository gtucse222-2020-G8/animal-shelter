package cse222.group8.server;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import cse222.group8.server.DataStructures.BinarySearchTree;
import cse222.group8.server.clientModels.*;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.plugin.json.JavalinJackson;

import java.util.Map;
import java.util.HashMap;
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
    public String createUserToken(String username) {
        return JWT.create().withClaim("Username", username)
                .withClaim("IsShelter", false)
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
        app.get("/shelters/animals/diseased",this::getMostDiseasedAnimal);
        app.post("/shelters/animals/diseased",this::addDiseasedAnimal);
        app.post("/shelters/animals/diseased/pop",this::deleteMostDiseasedAnimalFromQueue);
        // user routes
        app.post("/user/login",this::userLogin);    //HashMap<String,String> UserName Password
        app.post("/user/register",this::userRegister); //HashMap<String,String> UserName Password Email Name City Town
        app.get("/animals/filter/get",this::getFilteredAnimals); //Header City Town
        app.get("/user/favoritePets",this::getFavoriteAnimals); //Header Username Mail
        app.get("/user/account/update",this::updateUser); //Header Username Mail NewUsername NewMail NewPassword
        app.get("/user/ownages/requests/get",this::getAllOwnershipRequests); //Header Username Mail
        app.post("/user/ownages/requests/add",this::newOwnershipRequest); //HashMap<String,String> UserName Password Email City Town Shelter AnimalId
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
        ObjectMapper mapper = JavalinJackson.getObjectMapper();
        RegisterShelterData data;
        try {
            data = mapper.readValue(ctx.body(), RegisterShelterData.class);
            City city = system.getCity(data.city);
            Town town = city.getTown(data.town);
            system.addNewShelterRequest(new ShelterRequest(data.city,data.town,new Shelter(data.shelterName,city,town,data.catCapacity,data.dogCapacity,data.address,data.phoneNumber,data.password,system)));
        } catch (Exception e) {
            ctx.status(432);
            return;
        }
    }
    private void getShelterRegisterationStatus(Context ctx){
        String cityName = ctx.header("City");
        String townName = ctx.header("Town");
        String shelterName = ctx.header("ShelterName");
        Shelter shelter = system.getShelter(cityName,townName,shelterName);
        ctx.status(200);
        ctx.json(shelter.isRegistered());
    }
    private void shelterLogin(Context ctx){
        ObjectMapper mapper = JavalinJackson.getObjectMapper();
        LoginShelterData data;
        try {
            data = mapper.readValue(ctx.body(), LoginShelterData.class);
            City city = system.getCity(data.city);
            Town town = city.getTown(data.town);
            Shelter shelter = town.getShelter(data.shelterName);
            if(shelter.getPassword() == data.password){
                Token token = new Token();
                token.accessToken = createShelterToken(data.city,data.town,data.shelterName);
                ctx.status(200);
                ctx.json(token);
            }else{
                ctx.status(403);
            }
        } catch (Exception e) {
            ctx.status(432);
            return;
        }
    }
    private void getAnimal(Context ctx){
        if(hasJwt(ctx)){
            DecodedJWT jwt = verifyBearer(ctx.header("Authorization"));
            if(jwt!=null){
                if(jwt.getClaim("IsShelter").asBoolean()) {
                    Shelter shelter = getShelterFromJWT(jwt);
                    try {
                        int animalId = Integer.parseInt(ctx.header("AnimalId"));
                        Animal animal;
                        String species;
                        if(animalId%2==1){
                            animal = shelter.getCat(animalId);
                            species = "Cat";
                        }
                        else{
                            animal = shelter.getDog(animalId);
                            species = "Dog";
                        }
                        ctx.json(new AnimalData(animal.getId(),animal.getName(),species,animal.getKind(),animal.getGender(),animal.getAge(),animal.getVaccination(),animal.isNeutered(),animal.getInfo(),animal.getAdoptionRequest()!=null));
                    }catch (NumberFormatException ignore){
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
    private void updateAnimalData(Context ctx){
        if(hasJwt(ctx)){
            DecodedJWT jwt = verifyBearer(ctx.header("Authorization"));
            if(jwt!=null){
                if(jwt.getClaim("IsShelter").asBoolean()) {
                    Shelter shelter = getShelterFromJWT(jwt);
                    ObjectMapper mapper = JavalinJackson.getObjectMapper();
                    AnimalData data;
                    try {
                        data = mapper.readValue(ctx.body(), AnimalData.class);
                        Animal animal;
                        boolean isCat = false;
                        if(data.id%2==1){
                            isCat = true;
                            animal = shelter.getCat(data.id);
                        }
                        else{
                            animal = shelter.getDog(data.id);
                        }
                        animal.updateAnimal(new Animal(data.name,data.kind,data.age,isCat));
                        animal.setInfo(data.info);
                        animal.setGender(data.gender);
                        animal.setVaccination(data.vaccination);
                        animal.setNeutered(data.neutered);
                        ctx.status(200);
                        return;
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
    private void updateAnimalPicture(Context ctx){
        if(hasJwt(ctx)){
            DecodedJWT jwt = verifyBearer(ctx.header("Authorization"));
            if(jwt!=null){
                if(jwt.getClaim("IsShelter").asBoolean()) {
                    Shelter shelter = getShelterFromJWT(jwt);
                    ObjectMapper mapper = JavalinJackson.getObjectMapper();
                    String data;
                    try {
                        int animalId = Integer.parseInt(ctx.header("AnimalId"));
                        data = mapper.readValue(ctx.body(), String.class);
                        Animal animal;
                        String species;
                        if(animalId%2==1){
                            animal = shelter.getCat(animalId);
                        }
                        else{
                            animal = shelter.getDog(animalId);
                        }
                        animal.setImageString(data);
                    }catch (NumberFormatException ignore){
                        ctx.status(432);
                        return;
                    }catch (Exception ignore){
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
    private void getShelterCats(Context ctx){
        if(hasJwt(ctx)){
            DecodedJWT jwt = verifyBearer(ctx.header("Authorization"));
            if(jwt!=null){
                if(jwt.getClaim("IsShelter").asBoolean()) {
                    Shelter shelter = getShelterFromJWT(jwt);
                    BinarySearchTree<Animal> cats = shelter.getCats();
                    List<AnimalDataWithImage> data = new LinkedList<AnimalDataWithImage>();
                    for(Animal cat: cats){
                        data.add(new AnimalDataWithImage(cat.getId(),cat.getName(),"cat",cat.getKind(),cat.getGender(),cat.getAge(),cat.getVaccination(),cat.isNeutered(),cat.getInfo(),cat.getAdoptionRequest()!=null,cat.getImageString()));
                    }
                    ctx.status(200);
                    ctx.json((AnimalDataWithImage[])data.toArray());
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
    private void getShelterDogs(Context ctx){
        if(hasJwt(ctx)){
            DecodedJWT jwt = verifyBearer(ctx.header("Authorization"));
            if(jwt!=null){
                if(jwt.getClaim("IsShelter").asBoolean()) {
                    Shelter shelter = getShelterFromJWT(jwt);
                    BinarySearchTree<Animal> dogs = shelter.getDogs();
                    List<AnimalDataWithImage> data = new LinkedList<AnimalDataWithImage>();
                    for(Animal dog: dogs){
                        data.add(new AnimalDataWithImage(dog.getId(),dog.getName(),"dog",dog.getKind(),dog.getGender(),dog.getAge(),dog.getVaccination(),dog.isNeutered(),dog.getInfo(),dog.getAdoptionRequest()!=null,dog.getImageString()));
                    }
                    ctx.status(200);
                    ctx.json((AnimalDataWithImage[])data.toArray());
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
    private void addNewCat(Context ctx){
        if(hasJwt(ctx)){
            DecodedJWT jwt = verifyBearer(ctx.header("Authorization"));
            if(jwt!=null){
                if(jwt.getClaim("IsShelter").asBoolean()) {
                    Shelter shelter = getShelterFromJWT(jwt);
                    ObjectMapper mapper = JavalinJackson.getObjectMapper();
                    AnimalDataWithImage data;
                    try {
                        data = mapper.readValue(ctx.body(), AnimalDataWithImage.class);
                        Animal cat = new Animal(data.name, data.kind, data.age, true);
                        cat.setInfo(data.info);
                        cat.setGender(data.gender);
                        cat.setVaccination(data.vaccination);
                        cat.setNeutered(data.neutered);
                        shelter.addCat(cat);
                    }catch (Exception ignore){
                        ctx.status(403);
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
    private void addNewDog(Context ctx){
        if(hasJwt(ctx)){
            DecodedJWT jwt = verifyBearer(ctx.header("Authorization"));
            if(jwt!=null){
                if(jwt.getClaim("IsShelter").asBoolean()) {
                    Shelter shelter = getShelterFromJWT(jwt);
                    ObjectMapper mapper = JavalinJackson.getObjectMapper();
                    AnimalDataWithImage data;
                    try {
                        data = mapper.readValue(ctx.body(), AnimalDataWithImage.class);
                        Animal dog = new Animal(data.name, data.kind, data.age, false);
                        dog.setInfo(data.info);
                        dog.setGender(data.gender);
                        dog.setVaccination(data.vaccination);
                        dog.setNeutered(data.neutered);
                        shelter.addCat(dog);
                        ctx.status(200);
                    }catch (Exception ignore){
                        ctx.status(403);
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
    private void shelterUpdatePassword(Context ctx){
        if(hasJwt(ctx)){
            DecodedJWT jwt = verifyBearer(ctx.header("Authorization"));
            if(jwt!=null){
                if(jwt.getClaim("IsShelter").asBoolean()) {
                    Shelter shelter = getShelterFromJWT(jwt);
                    ObjectMapper mapper = JavalinJackson.getObjectMapper();
                    String data;
                    try {
                        data = mapper.readValue(ctx.body(), String.class);
                        shelter.setPassword(data);
                    }catch (Exception ignore){
                        ctx.status(432);
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
    private void shelterUpdateCapacity(Context ctx){
        if(hasJwt(ctx)){
            DecodedJWT jwt = verifyBearer(ctx.header("Authorization"));
            if(jwt!=null){
                if(jwt.getClaim("IsShelter").asBoolean()) {
                    Shelter shelter = getShelterFromJWT(jwt);
                    ObjectMapper mapper = JavalinJackson.getObjectMapper();
                    int data;
                    try {
                        data = mapper.readValue(ctx.body(), int.class);
                        String animalType = ctx.header("AnimalType");
                        if(animalType == "Cat"){
                            shelter.makeCapChangeRequest(data,shelter.getDogCapacity());
                        }
                        else if(animalType == "Dog"){
                            shelter.makeCapChangeRequest(shelter.getCatCapacity(),data);
                        }
                    }catch (Exception ignore){
                        ctx.status(432);
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
    private void shelterUpdateName(Context ctx){
        if(hasJwt(ctx)){
            DecodedJWT jwt = verifyBearer(ctx.header("Authorization"));
            if(jwt!=null){
                if(jwt.getClaim("IsShelter").asBoolean()) {
                    Shelter shelter = getShelterFromJWT(jwt);
                    ObjectMapper mapper = JavalinJackson.getObjectMapper();
                    String data;
                    try {
                        data = mapper.readValue(ctx.body(), String.class);
                        shelter.setName(data);
                    }catch (Exception ignore){
                        ctx.status(432);
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
    private void getShelterAdoptionRequest(Context ctx){
        if(hasJwt(ctx)){
            DecodedJWT jwt = verifyBearer(ctx.header("Authorization"));
            if(jwt!=null){
                if(jwt.getClaim("IsShelter").asBoolean()) {
                    Shelter shelter = getShelterFromJWT(jwt);
                    List<AdoptionRequest> adoptionRequests = shelter.getAdoptionRequests();
                    AdoptionRequestData[] data = new AdoptionRequestData[adoptionRequests.size()];
                    int index = 0;
                    for(AdoptionRequest adoptionRequest : adoptionRequests){
                        AdoptionRequestData adoptionRequestData = new AdoptionRequestData();
                        adoptionRequestData.requester = adoptionRequest.getRequester().getName();
                        adoptionRequestData.requestId = index;
                        adoptionRequestData.image = adoptionRequest.getRequestedAnimal().getImageString();
                        adoptionRequestData.animalId = adoptionRequest.getRequestedAnimal().getId();
                        data[index] = adoptionRequestData;
                        ++index;
                    }
                    ctx.status(200);
                    ctx.json(data);
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
    private void approveAdoptionRequest(Context ctx){
        if(hasJwt(ctx)){
            DecodedJWT jwt = verifyBearer(ctx.header("Authorization"));
            if(jwt!=null){
                if(jwt.getClaim("IsShelter").asBoolean()) {
                    Shelter shelter = getShelterFromJWT(jwt);
                    ObjectMapper mapper = JavalinJackson.getObjectMapper();
                    int data;
                    try {
                        data = mapper.readValue(ctx.body(), int.class);
                        shelter.approveAdoptionRequest(data);
                        ctx.status(200);
                    }catch (Exception ignore){
                        ctx.status(432);
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
    private void getDailyTasks(Context ctx){
        if(hasJwt(ctx)){
            DecodedJWT jwt = verifyBearer(ctx.header("Authorization"));
            if(jwt!=null){
                if(jwt.getClaim("IsShelter").asBoolean()) {
                    Shelter shelter = getShelterFromJWT(jwt);
                    List<Task> tasks = shelter.getTasks();
                    TaskData[] data = new TaskData[tasks.size()];
                    int i = 0;
                    for(Task task : tasks){
                        TaskData taskData = new TaskData();
                        taskData.id = i;
                        taskData.status = task.getStatus();
                        taskData.text = task.getTask();
                        data[i] = taskData;
                        ++i;
                    }
                    ctx.status(200);
                    ctx.json(data);
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
    private void createDailyTask(Context ctx){
        if(hasJwt(ctx)){
            DecodedJWT jwt = verifyBearer(ctx.header("Authorization"));
            if(jwt!=null){
                if(jwt.getClaim("IsShelter").asBoolean()) {
                    Shelter shelter = getShelterFromJWT(jwt);
                    ObjectMapper mapper = JavalinJackson.getObjectMapper();
                    TaskData data;
                    try {
                        data = mapper.readValue(ctx.body(), TaskData.class);
                        shelter.getTasks().add(new Task(data.text,data.status));
                        ctx.status(200);
                    }
                    catch (Exception ignore){
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
    private void deleteDailyTask(Context ctx){
        if(hasJwt(ctx)){
            DecodedJWT jwt = verifyBearer(ctx.header("Authorization"));
            if(jwt!=null){
                if(jwt.getClaim("IsShelter").asBoolean()) {
                    Shelter shelter = getShelterFromJWT(jwt);
                    ObjectMapper mapper = JavalinJackson.getObjectMapper();
                    int data;
                    try {
                        data = mapper.readValue(ctx.body(), int.class);
                        shelter.getTasks().remove(data);
                        ctx.status(200);
                    }
                    catch (Exception ignore){
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
    private void updateDailyTask(Context ctx){
        if(hasJwt(ctx)){
            DecodedJWT jwt = verifyBearer(ctx.header("Authorization"));
            if(jwt!=null){
                if(jwt.getClaim("IsShelter").asBoolean()) {
                    Shelter shelter = getShelterFromJWT(jwt);
                    ObjectMapper mapper = JavalinJackson.getObjectMapper();
                    TaskData data;
                    try {
                        data = mapper.readValue(ctx.body(), TaskData.class);
                        Task task = shelter.getTasks().get(data.id);
                        task.setStatus(data.status);
                        task.setTask(data.text);
                        ctx.status(200);
                    }
                    catch (Exception ignore){
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
    private void approveDailyTask(Context ctx){
        if(hasJwt(ctx)){
            DecodedJWT jwt = verifyBearer(ctx.header("Authorization"));
            if(jwt!=null){
                if(jwt.getClaim("IsShelter").asBoolean()) {
                    Shelter shelter = getShelterFromJWT(jwt);
                    ObjectMapper mapper = JavalinJackson.getObjectMapper();
                    int data;
                    try {
                        data = mapper.readValue(ctx.body(), int.class);
                        Task task = shelter.getTasks().get(data);
                        task.setStatus(true);
                        ctx.status(200);
                    }
                    catch (Exception ignore){
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
    public void getMostDiseasedAnimal(Context ctx){
        if(hasJwt(ctx)){
            DecodedJWT jwt = verifyBearer(ctx.header("Authorization"));
            if(jwt!=null){
                if(jwt.getClaim("IsShelter").asBoolean()) {
                    Shelter shelter = getShelterFromJWT(jwt);
                    Animal animal = shelter.getDiseasedAnimals().peek().getAnimal();
                    ctx.json(new AnimalData(animal.getId(),animal.getName(),animal.getId()%2==1?"Cat":"Dog",animal.getKind(),animal.getGender(),animal.getAge(),animal.getVaccination(),animal.isNeutered(),animal.getInfo(),animal.getAdoptionRequest()!=null));
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
    public void addDiseasedAnimal(Context ctx){
        if(hasJwt(ctx)){
            DecodedJWT jwt = verifyBearer(ctx.header("Authorization"));
            if(jwt!=null){
                if(jwt.getClaim("IsShelter").asBoolean()) {
                    Shelter shelter = getShelterFromJWT(jwt);
                    try {
                        int animalId = Integer.parseInt(ctx.header("AnimalId"));
                        int diseaseLevel = Integer.parseInt(ctx.header("DiseaseLevel"));
                        shelter.addDiseasedAnimal(animalId, diseaseLevel);
                        ctx.status(200);
                    }catch (NumberFormatException ignore){
                        ctx.status(432);
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
    public void deleteMostDiseasedAnimalFromQueue(Context ctx){
        if(hasJwt(ctx)){
            DecodedJWT jwt = verifyBearer(ctx.header("Authorization"));
            if(jwt!=null){
                if(jwt.getClaim("IsShelter").asBoolean()) {
                    Shelter shelter = getShelterFromJWT(jwt);
                    Animal animal = shelter.getDiseasedAnimals().poll().getAnimal();
                    animal.setDiseased(0);
                    ctx.status(200);
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

    // User routes

        public void userLogin(Context ctx){
            ObjectMapper mapper = JavalinJackson.getObjectMapper();
            HashMap<String, String> data;
            try {
                data = mapper.readValue(ctx.body(), HashMap.class);
                String username = data.get("UserName");
                String password = data.get("Password");
                if(system.getUser(username).getPassword()==password){
                    ctx.status(200);
                    ctx.json(createUserToken(username));
                }else{
                    ctx.status(403);
                }
            } catch (Exception e) {
                ctx.status(432);
                return;
            }
        }    //HashMap<String,String> UserName Password
        public void userRegister(Context ctx){
            ObjectMapper mapper = JavalinJackson.getObjectMapper();
            HashMap<String, String> data;
            try {
                data = mapper.readValue(ctx.body(), HashMap.class);
                String username = data.get("UserName");
                if(system.getUser(username)==null){
                    String password = data.get("Password");
                    String email = data.get("Email");
                    String name = data.get("Name");
                    String city = data.get("City");
                    String town = data.get("Town");
                    User user = new User();
                    user.setCity(system.getCity(city));
                    user.setTown(system.getCity(city).getTown(town));
                    user.setName(name);
                    user.setUsername(username);
                    user.setEmail(email);
                    user.setPassword(password);
                    ctx.status(200);
                    ctx.json(createUserToken(username));
                }else{
                    ctx.status(304);
                }
            } catch (Exception e) {
                ctx.status(432);
                return;
            }
        } //HashMap<String,String> UserName Password Email Name City Town
        public void getFilteredAnimals(Context ctx){
            try {
                String cityName = ctx.header("City");
                String townName = ctx.header("Town");
                Town town = system.getCity(cityName).getTown(townName);
                List<AnimalData> data = new LinkedList<AnimalData>();
                for(Shelter shelter : town.getShelters()){
                    for(Animal animal : shelter.getCats()){
                        data.add(new AnimalData(animal.getId(),animal.getName(),"cat",animal.getKind(),animal.getGender(),animal.getAge(),animal.getVaccination(),animal.isNeutered(),animal.getInfo(),animal.getAdoptionRequest()!=null));
                    }
                    for(Animal animal : shelter.getDogs()){
                        data.add(new AnimalData(animal.getId(),animal.getName(),"dog",animal.getKind(),animal.getGender(),animal.getAge(),animal.getVaccination(),animal.isNeutered(),animal.getInfo(),animal.getAdoptionRequest()!=null));
                    }
                }
                ctx.status(200);
                ctx.json((AnimalData[])data.toArray());
            }catch (Exception ignore){
                ctx.status(432);
            }
        } //Header City Town
        public void getFavoriteAnimals(Context ctx){
            try {
                String username = ctx.header("Username");
                String email = ctx.header("Mail");
                List<AnimalData> data = new LinkedList<AnimalData>();
                for(Animal animal : system.getUser(username).getFavorites()){
                    String species = "Dog";
                    if(animal.getId()%2==1){
                        species = "Cat";
                    }
                    data.add(new AnimalData(animal.getId(),animal.getName(),species,animal.getKind(),animal.getGender(),animal.getAge(),animal.getVaccination(),animal.isNeutered(),animal.getInfo(),animal.getAdoptionRequest()!=null));
                }
                ctx.status(200);
                ctx.json((AnimalData[])data.toArray());
            }catch (Exception ignore){
                ctx.status(432);
            }
        } //Header Username Mail
        public void updateUser(Context ctx){
            try {
                String username = ctx.header("Username");
                String email = ctx.header("Mail");
                String newUsername = ctx.header("NewUsername");
                String newPassword = ctx.header("NewPassword");
                system.getUser(username).setPassword(newPassword);
                system.getUser(username).setUsername(newUsername);
                ctx.status(200);
            }catch (Exception ignore){
                ctx.status(432);
            }
        } //Header Username Mail NewUsername NewMail NewPassword
        public void getAllOwnershipRequests(Context ctx){
            try {
                String username = ctx.header("Username");
                String email = ctx.header("Mail");
                List<String> data = new LinkedList<String>();
                for(AdoptionRequest request : system.getUser(username).getRequests()){
                    data.add(request.getRequestedAnimal().getCity().getName() + " " +
                            request.getRequestedAnimal().getTown().getName() + " Animal ID: "+
                            request.getRequestedAnimal().getId()+" Expires at: " + request.getExpirationDate().toString());
                }
                ctx.status(200);
                ctx.json((String[])data.toArray());
            }catch (Exception ignore){
                ctx.status(432);
            }
        } //Header Username Mail
        public void newOwnershipRequest(Context ctx){
            ObjectMapper mapper = JavalinJackson.getObjectMapper();
            HashMap<String, String> data;
            try {
                data = mapper.readValue(ctx.body(), HashMap.class);
                String username = data.get("UserName");
                String password = data.get("Password");
                if(system.getUser(username)!=null && system.getUser(username).getPassword()==password){
                    String email = data.get("Email");
                    String name = data.get("Name");
                    String city = data.get("City");
                    String town = data.get("Town");
                    String shelter = data.get("Shelter");
                    int id = Integer.parseInt(data.get("AnimalId"));
                    Animal animal;
                    if(id%2==1){
                        animal = system.getCity(city).getTown(town).getShelter(shelter).getCat(id);
                    }
                    else{
                        animal = system.getCity(city).getTown(town).getShelter(shelter).getDog(id);
                    }
                    animal.makeARequest(system.getUser(username));
                    ctx.status(200);
                    ctx.json(createUserToken(username));
                }else{
                    ctx.status(304);
                }
            } catch (Exception e) {
                ctx.status(432);
                return;
            }
        } //HashMap<String,String> UserName Password Email City Town Shelter AnimalId

}
