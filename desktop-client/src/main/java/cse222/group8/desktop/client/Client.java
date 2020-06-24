package cse222.group8.desktop.client;

import cse222.group8.desktop.client.models.*;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * The type Client.
 */
public class Client {
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final String baseUrl = "http://localhost:8080/";

    /**
     * Get cities string [ ].
     *
     * @return the string [ ]
     * @throws ConnectionError the connection error
     */
    public static String[] getCities() throws ConnectionError {
        URI uri = URI.create(baseUrl+"cities");
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .setHeader("Content-Type", "application/json")
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200){
                String jsonBody = response.body();
                Gson gson = new Gson();
                return gson.fromJson(jsonBody,String[].class);
            }
            else {
                System.out.println(response.statusCode());
                System.out.println(response.body());
                throw new ConnectionError();
            }
        } catch (IOException | InterruptedException e) {
            throw new ConnectionError();
        }
    }

    /**
     * City exists boolean.
     *
     * @param city the city
     * @return the boolean
     * @throws ConnectionError the connection error
     */
    public static boolean cityExists(String city) throws ConnectionError {
        URI uri = URI.create(baseUrl+"cities/check");
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .setHeader("Content-Type", "application/json")
                .setHeader("City", city)
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200){
                String jsonBody = response.body();
                Gson gson = new Gson();
                return gson.fromJson(jsonBody,boolean.class);
            }
            else {
                System.out.println(response.statusCode());
                System.out.println(response.body());
                throw new ConnectionError();
            }
        } catch (IOException | InterruptedException e) {
            throw new ConnectionError();
        }
    }

    /**
     * Get towns string [ ].
     *
     * @param city the city
     * @return the string [ ]
     * @throws ConnectionError the connection error
     */
    public static String[] getTowns(String city) throws ConnectionError {
        URI uri = URI.create(baseUrl+"cities/towns");
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .setHeader("Content-Type", "application/json")
                .setHeader("City", city)
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200){
                String jsonBody = response.body();
                Gson gson = new Gson();
                return gson.fromJson(jsonBody,String[].class);
            }
            else {
                System.out.println(response.statusCode());
                System.out.println(response.body());
                throw new ConnectionError();
            }
        } catch (IOException | InterruptedException e) {
            throw new ConnectionError();
        }
    }

    /**
     * Town exists boolean.
     *
     * @param city the city
     * @param town the town
     * @return the boolean
     * @throws ConnectionError the connection error
     */
    public static boolean townExists(String city, String town) throws ConnectionError {
        URI uri = URI.create(baseUrl+"cities/towns/check");
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .setHeader("Content-Type", "application/json")
                .setHeader("City", city)
                .setHeader("Town",town)
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200){
                String jsonBody = response.body();
                Gson gson = new Gson();
                return gson.fromJson(jsonBody,boolean.class);
            }
            else {
                System.out.println(response.statusCode());
                System.out.println(response.body());
                throw new ConnectionError();
            }
        } catch (IOException | InterruptedException e) {
            throw new ConnectionError();
        }
    }

    /**
     * Get shelters string [ ].
     *
     * @param city the city
     * @param town the town
     * @return the string [ ]
     * @throws ConnectionError the connection error
     */
    public static String[] getShelters(String city, String town) throws ConnectionError{
        URI uri = URI.create(baseUrl+"cities/towns/shelters");
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .setHeader("Content-Type", "application/json")
                .setHeader("City", city)
                .setHeader("Town",town)
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200){
                String jsonBody = response.body();
                Gson gson = new Gson();
                return gson.fromJson(jsonBody,String[].class);
            }
            else {
                System.out.println(response.statusCode());
                System.out.println(response.body());
                throw new ConnectionError();
            }
        } catch (IOException | InterruptedException e) {
            throw new ConnectionError();
        }
    }

    /**
     * Shelter exists boolean.
     *
     * @param city    the city
     * @param town    the town
     * @param shelter the shelter
     * @return the boolean
     * @throws ConnectionError the connection error
     */
    public static boolean shelterExists(String city, String town, String shelter) throws ConnectionError {
        URI uri = URI.create(baseUrl+"cities/towns/shelters/check");
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .setHeader("Content-Type", "application/json")
                .setHeader("City", city)
                .setHeader("Town",town)
                .setHeader("Shelter", shelter)
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200){
                String jsonBody = response.body();
                Gson gson = new Gson();
                return gson.fromJson(jsonBody,boolean.class);
            }
            else {
                System.out.println(response.statusCode());
                System.out.println(response.body());
                throw new ConnectionError();
            }
        } catch (IOException | InterruptedException e) {
            throw new ConnectionError();
        }
    }

    /**
     * Gets general shelter data.
     *
     * @param token the token
     * @return the general shelter data
     * @throws ConnectionError the connection error
     */
    public static GeneralShelterData getGeneralShelterData(Token token) throws ConnectionError{
        URI uri = URI.create(baseUrl+"shelters");
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Bearer "+token.accessToken)
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200){
                String jsonBody = response.body();
                Gson gson = new Gson();
                return gson.fromJson(jsonBody, GeneralShelterData.class);
            }
            else {
                System.out.println(response.statusCode());
                System.out.println(response.body());
                throw new ConnectionError();
            }
        } catch (IOException | InterruptedException e) {
            throw new ConnectionError();
        }
    }

    /**
     * Create shelter token.
     *
     * @param city        the city
     * @param town        the town
     * @param shelterName the shelter name
     * @param phoneNumber the phone number
     * @param password    the password
     * @param catCapacity the cat capacity
     * @param dogCapacity the dog capacity
     * @param address     the address
     * @return the token
     * @throws ConnectionError the connection error
     */
    public static Token createShelter(String city, String town, String shelterName, String phoneNumber, String password, int catCapacity, int dogCapacity, String address) throws ConnectionError{
        URI uri = URI.create(baseUrl+"shelters");
        Gson gson = new Gson();
        String body = gson.toJson(new RegisterShelterData(city, town, shelterName, phoneNumber, password, address, catCapacity, dogCapacity));
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .uri(uri)
                .setHeader("Content-Type", "application/json")
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200){
                String jsonBody = response.body();
                return gson.fromJson(jsonBody, Token.class);
            }
            else {
                System.out.println(response.statusCode());
                System.out.println(response.body());
                throw new ConnectionError();
            }
        } catch (IOException | InterruptedException e) {
            throw new ConnectionError();
        }
    }

    /**
     * Gets shelter status.
     *
     * @param city        the city
     * @param town        the town
     * @param shelterName the shelter name
     * @return the shelter status
     * @throws ConnectionError the connection error
     */
    public static boolean getShelterStatus(String city, String town, String shelterName) throws ConnectionError{
        URI uri = URI.create(baseUrl+"shelters/registeration");
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .setHeader("Content-Type", "application/json")
                .setHeader("City",city)
                .setHeader("Town",town)
                .setHeader("ShelterName",shelterName)
                .uri(uri)
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200){
                String jsonBody = response.body();
                Gson gson = new Gson();
                return gson.fromJson(jsonBody, boolean.class);
            }
            else {
                System.out.println(response.statusCode());
                System.out.println(response.body());
                throw new ConnectionError();
            }
        } catch (IOException | InterruptedException e) {
            throw new ConnectionError();
        }
    }

    /**
     * Login token.
     *
     * @param city        the city
     * @param town        the town
     * @param shelterName the shelter name
     * @param password    the password
     * @return the token
     * @throws ConnectionError        the connection error
     * @throws WrongPasswordException the wrong password exception
     */
    public static Token login(String city, String town, String shelterName, String password) throws ConnectionError, WrongPasswordException {
        URI uri = URI.create(baseUrl+"shelters/login");
        Gson gson = new Gson();
        String body = gson.toJson(new LoginShelterData(city,town,shelterName,password));
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .setHeader("Content-Type", "application/json")
                .uri(uri)
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200){
                String jsonBody = response.body();
                return gson.fromJson(jsonBody, Token.class);
            }
            else if(response.statusCode()==403){
                throw new WrongPasswordException();
            }
            else {
                System.out.println(response.statusCode());
                System.out.println(response.body());
                throw new ConnectionError();
            }
        } catch (IOException | InterruptedException e) {
            throw new ConnectionError();
        }
    }

    /**
     * Gets animal.
     *
     * @param token    the token
     * @param animalId the animal ıd
     * @return the animal
     * @throws ConnectionError the connection error
     */
    public static AnimalDataWithImage getAnimal(Token token, int animalId) throws ConnectionError, NotFound {
        URI uri = URI.create(baseUrl+"shelters/animals");
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Bearer "+token.accessToken)
                .setHeader("AnimalId", String.valueOf(animalId))
                .uri(uri)
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200){
                String jsonBody = response.body();
                Gson gson = new Gson();
                return gson.fromJson(jsonBody, AnimalDataWithImage.class);
            }
            else if (response.statusCode()==404){
                throw new NotFound();
            }
            else {
                System.out.println(response.statusCode());
                System.out.println(response.body());
                throw new ConnectionError();
            }
        } catch (IOException | InterruptedException e) {
            throw new ConnectionError();
        }
    }

    /**
     * Update animal data.
     *
     * @param token      the token
     * @param animalData the animal data
     * @throws ConnectionError the connection error
     */
    public static void updateAnimalData(Token token, AnimalData animalData) throws ConnectionError {
        URI uri = URI.create(baseUrl+"shelters/animals/update");
        Gson gson = new Gson();
        String body = gson.toJson(animalData);
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Bearer "+token.accessToken)
                .uri(uri)
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200){
                String jsonBody = response.body();
            }
            else {
                System.out.println(response.statusCode());
                System.out.println(response.body());
                throw new ConnectionError();
            }
        } catch (IOException | InterruptedException e) {
            throw new ConnectionError();
        }
    }

    /**
     * Update animal picture.
     *
     * @param token    the token
     * @param animalId the animal ıd
     * @param image    the image
     * @throws ConnectionError the connection error
     */
    public static void updateAnimalPicture(Token token, int animalId, String image) throws ConnectionError {
        URI uri = URI.create(baseUrl+"shelters/animals/update/picture");
        Gson gson = new Gson();
        String body = gson.toJson(image);
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Bearer "+token.accessToken)
                .setHeader("AnimalId",String.valueOf(animalId))
                .uri(uri)
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200){
                String jsonBody = response.body();
            }
            else {
                System.out.println(response.statusCode());
                System.out.println(response.body());
                throw new ConnectionError();
            }
        } catch (IOException | InterruptedException e) {
            throw new ConnectionError();
        }
    }
    private static AnimalDataWithImage[] getAnimals(Token token, String animal) throws ConnectionError {
        URI uri = URI.create(baseUrl+"shelters/animals/"+animal);
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Bearer "+token.accessToken)
                .uri(uri)
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200){
                String jsonBody = response.body();
                Gson gson = new Gson();
                return gson.fromJson(jsonBody, AnimalDataWithImage[].class);
            }
            else {
                System.out.println(response.statusCode());
                System.out.println(response.body());
                throw new ConnectionError();
            }
        } catch (IOException | InterruptedException e) {
            throw new ConnectionError();
        }
    }

    /**
     * Get cats animal data with ımage [ ].
     *
     * @param token the token
     * @return the animal data with ımage [ ]
     * @throws ConnectionError the connection error
     */
    public static AnimalDataWithImage[] getCats(Token token) throws ConnectionError {
        return getAnimals(token,"cats");
    }

    /**
     * Get dogs animal data with ımage [ ].
     *
     * @param token the token
     * @return the animal data with ımage [ ]
     * @throws ConnectionError the connection error
     */
    public static AnimalDataWithImage[] getDogs(Token token) throws ConnectionError {
        return getAnimals(token,"dogs");
    }
    private static void addAnimal(Token token, AnimalData animalData, String imageFile,String animal) throws ConnectionError {
        URI uri = URI.create(baseUrl+"shelters/animals/"+animal);
        Gson gson = new Gson();
        String body = gson.toJson(new AnimalDataWithImage(animalData,imageFile));
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Bearer "+token.accessToken)
                .uri(uri)
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200){
                String jsonBody = response.body();
            }
            else {
                System.out.println(response.statusCode());
                System.out.println(response.body());
                throw new ConnectionError();
            }
        } catch (IOException | InterruptedException e) {
            throw new ConnectionError();
        }
    }

    /**
     * Add cat.
     *
     * @param token      the token
     * @param animalData the animal data
     * @param imageFile  the image file
     * @throws ConnectionError the connection error
     */
    public static void addCat(Token token, AnimalData animalData, String imageFile) throws ConnectionError {
        addAnimal(token,animalData,imageFile,"cats");
    }

    /**
     * Add dog.
     *
     * @param token      the token
     * @param animalData the animal data
     * @param imageFile  the image file
     * @throws ConnectionError the connection error
     */
    public static void addDog(Token token, AnimalData animalData, String imageFile) throws ConnectionError {
        addAnimal(token,animalData,imageFile,"dogs");
    }

    /**
     * Update password.
     *
     * @param token    the token
     * @param password the password
     * @throws ConnectionError the connection error
     */
    public static void updatePassword(Token token, String password) throws ConnectionError {
        URI uri = URI.create(baseUrl+"shelters/update/password");
        Gson gson = new Gson();
        String body = gson.toJson(password);
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Bearer "+token.accessToken)
                .uri(uri)
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200){
                String jsonBody = response.body();
            }
            else {
                System.out.println(response.statusCode());
                System.out.println(response.body());
                throw new ConnectionError();
            }
        } catch (IOException | InterruptedException e) {
            throw new ConnectionError();
        }
    }

    /**
     * Update capacity.
     *
     * @param token      the token
     * @param capacity   the capacity
     * @param animalType the animal type
     * @throws ConnectionError the connection error
     */
    public static void updateCapacity(Token token, int capacity, String animalType) throws ConnectionError {
        URI uri = URI.create(baseUrl+"shelters/update/capacity");
        Gson gson = new Gson();
        String body = gson.toJson(capacity);
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .setHeader("Content-Type", "application/json")
                .setHeader("AnimalType", animalType)
                .setHeader("Authorization", "Bearer "+token.accessToken)
                .uri(uri)
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200){
                String jsonBody = response.body();
            }
            else {
                System.out.println(response.statusCode());
                System.out.println(response.body());
                throw new ConnectionError();
            }
        } catch (IOException | InterruptedException e) {
            throw new ConnectionError();
        }
    }

    /**
     * Update name.
     *
     * @param token the token
     * @param name  the name
     * @throws ConnectionError the connection error
     */
    public static void updateName(Token token, String name) throws ConnectionError {
        URI uri = URI.create(baseUrl+"shelters/update/name");
        Gson gson = new Gson();
        String body = gson.toJson(name);
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Bearer "+token.accessToken)
                .uri(uri)
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200){
                String jsonBody = response.body();
            }
            else {
                System.out.println(response.statusCode());
                System.out.println(response.body());
                throw new ConnectionError();
            }
        } catch (IOException | InterruptedException e) {
            throw new ConnectionError();
        }
    }

    /**
     * Get adoption requests adoption request data [ ].
     *
     * @param token the token
     * @return the adoption request data [ ]
     * @throws ConnectionError the connection error
     */
    public static AdoptionRequestData[] getAdoptionRequests(Token token) throws ConnectionError {
        URI uri = URI.create(baseUrl+"shelters/adoption-requests");
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Bearer "+token.accessToken)
                .uri(uri)
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200){
                String jsonBody = response.body();
                Gson gson = new Gson();
                return gson.fromJson(jsonBody, AdoptionRequestData[].class);
            }
            else {
                System.out.println(response.statusCode());
                System.out.println(response.body());
                throw new ConnectionError();
            }
        } catch (IOException | InterruptedException e) {
            throw new ConnectionError();
        }
    }

    /**
     * Approve adoption.
     *
     * @param token             the token
     * @param adoptionRequestId the adoption request ıd
     * @throws ConnectionError the connection error
     */
    public static void approveAdoption(Token token, int adoptionRequestId) throws ConnectionError, NotFound {
        URI uri = URI.create(baseUrl+"shelters/adoption-requests/approve");
        Gson gson = new Gson();
        String body = gson.toJson(adoptionRequestId);
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Bearer "+token.accessToken)
                .uri(uri)
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200){
                String jsonBody = response.body();
            }
            else if (response.statusCode()==404){
                throw new NotFound();
            }
            else {
                System.out.println(response.statusCode());
                System.out.println(response.body());
                throw new ConnectionError();
            }
        } catch (IOException | InterruptedException e) {
            throw new ConnectionError();
        }
    }

    /**
     * Get tasks task data [ ].
     *
     * @param token the token
     * @return the task data [ ]
     * @throws ConnectionError the connection error
     */
    public static TaskData[] getTasks(Token token) throws ConnectionError {
        URI uri = URI.create(baseUrl+"shelters/daily-tasks");
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Bearer "+token.accessToken)
                .uri(uri)
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200){
                String jsonBody = response.body();
                Gson gson = new Gson();
                return gson.fromJson(jsonBody, TaskData[].class);
            }
            else {
                System.out.println(response.statusCode());
                System.out.println(response.body());
                throw new ConnectionError();
            }
        } catch (IOException | InterruptedException e) {
            throw new ConnectionError();
        }
    }

    /**
     * Create task.
     *
     * @param token the token
     * @param task  the task
     * @throws ConnectionError the connection error
     */
    public static void createTask(Token token, TaskData task) throws ConnectionError {
        URI uri = URI.create(baseUrl+"shelters/daily-tasks");
        Gson gson = new Gson();
        String body = gson.toJson(task);
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Bearer "+token.accessToken)
                .uri(uri)
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200){
                String jsonBody = response.body();
            }
            else {
                System.out.println(response.statusCode());
                System.out.println(response.body());
                throw new ConnectionError();
            }
        } catch (IOException | InterruptedException e) {
            throw new ConnectionError();
        }
    }

    /**
     * Delete task.
     *
     * @param token  the token
     * @param taskId the task ıd
     * @throws ConnectionError the connection error
     */
    public static void deleteTask(Token token, int taskId) throws ConnectionError {
        URI uri = URI.create(baseUrl+"shelters/daily-tasks/delete");
        Gson gson = new Gson();
        String body = gson.toJson(taskId);
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Bearer "+token.accessToken)
                .uri(uri)
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200){
                String jsonBody = response.body();
            }
            else {
                System.out.println(response.statusCode());
                System.out.println(response.body());
                throw new ConnectionError();
            }
        } catch (IOException | InterruptedException e) {
            throw new ConnectionError();
        }
    }

    /**
     * Update task.
     *
     * @param token the token
     * @param task  the task
     * @throws ConnectionError the connection error
     */
    public static void updateTask(Token token, TaskData task) throws ConnectionError {
        URI uri = URI.create(baseUrl+"shelters/daily-tasks/update");
        Gson gson = new Gson();
        String body = gson.toJson(task);
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Bearer "+token.accessToken)
                .uri(uri)
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200){
                String jsonBody = response.body();
            }
            else {
                System.out.println(response.statusCode());
                System.out.println(response.body());
                throw new ConnectionError();
            }
        } catch (IOException | InterruptedException e) {
            throw new ConnectionError();
        }
    }

    /**
     * Approve task.
     *
     * @param token  the token
     * @param taskId the task ıd
     * @throws ConnectionError the connection error
     */
    public static void approveTask(Token token, int taskId) throws ConnectionError {
        URI uri = URI.create(baseUrl+"shelters/daily-tasks/approve");
        Gson gson = new Gson();
        String body = gson.toJson(taskId);
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Bearer "+token.accessToken)
                .uri(uri)
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200){
                String jsonBody = response.body();
            }
            else {
                System.out.println(response.statusCode());
                System.out.println(response.body());
                throw new ConnectionError();
            }
        } catch (IOException | InterruptedException e) {
            throw new ConnectionError();
        }
    }

    /**
     * Gets most diseased animal.
     *
     * @param token the token
     * @return the most diseased animal
     * @throws ConnectionError the connection error
     */
    public static AnimalData getMostDiseasedAnimal(Token token) throws ConnectionError, NotFound {
        URI uri = URI.create(baseUrl+"shelters/animals/diseased");
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Bearer "+token.accessToken)
                .uri(uri)
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200){
                String jsonBody = response.body();
                Gson gson = new Gson();
                return gson.fromJson(jsonBody, AnimalData.class);
            }
            else if(response.statusCode()==404){
                throw new NotFound();
            }
            else {
                System.out.println(response.statusCode());
                System.out.println(response.body());
                throw new ConnectionError();
            }
        } catch (IOException | InterruptedException e) {
            throw new ConnectionError();
        }
    }

    /**
     * Add diseased animal.
     *
     * @param token        the token
     * @param animalId     the animal ıd
     * @param diseaseLevel the disease level
     * @throws ConnectionError the connection error
     */
    public static void addDiseasedAnimal(Token token, int animalId, int diseaseLevel) throws ConnectionError {
        URI uri = URI.create(baseUrl+"shelters/animals/diseased");
        Gson gson = new Gson();
        String body = gson.toJson("");
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Bearer "+token.accessToken)
                .uri(uri)
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200){
                String jsonBody = response.body();
            }
            else {
                System.out.println(response.statusCode());
                System.out.println(response.body());
                throw new ConnectionError();
            }
        } catch (IOException | InterruptedException e) {
            throw new ConnectionError();
        }
    }
    /**
     * Cure diseased animal.
     *
     * @param token        the token
     * @throws ConnectionError the connection error
     */
    public static void cureDiseasedAnimal(Token token) throws ConnectionError {
        URI uri = URI.create(baseUrl+"shelters/animals/diseased/pop");
        Gson gson = new Gson();
        String body = gson.toJson("");
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Bearer "+token.accessToken)
                .uri(uri)
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200){
                String jsonBody = response.body();
            }
            else {
                System.out.println(response.statusCode());
                System.out.println(response.body());
                throw new ConnectionError();
            }
        } catch (IOException | InterruptedException e) {
            throw new ConnectionError();
        }
    }
}