package cse222.group8.desktop;

import cse222.group8.desktop.models.*;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Client {
    private final HttpClient client = HttpClient.newHttpClient();
    private final String baseUrl = "http://example.url/";

    public String[] getCities() throws ConnectionError {
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
    public String[] getTowns(String city) throws ConnectionError {
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
    public String[] getShelters(String city, String town) throws ConnectionError{
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
    public GeneralShelterData getGeneralShelterData(Token token) throws ConnectionError{
        URI uri = URI.create(baseUrl+"shelters");
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .setHeader("Content-Type", "application/json")
                .setHeader("Authentication", "Bearer "+token.accessToken)
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
    public Token createShelter(String city, String town, String shelterName, String phoneNumber, String password, int catCapacity, int dogCapacity, String address) throws ConnectionError{
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
    public int getShelterStatus(String city, String town, String shelterName) throws ConnectionError{
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
                return gson.fromJson(jsonBody, int.class);
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
    public Token login(String city, String town, String shelterName, String password) throws ConnectionError {
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
            else {
                System.out.println(response.statusCode());
                System.out.println(response.body());
                throw new ConnectionError();
            }
        } catch (IOException | InterruptedException e) {
            throw new ConnectionError();
        }
    }
    public AnimalData getAnimal(Token token, String city, String town, int animalId) throws ConnectionError {
        URI uri = URI.create(baseUrl+"shelters/animals");
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .setHeader("Content-Type", "application/json")
                .setHeader("City",city)
                .setHeader("Town",town)
                .setHeader("AnimalId", String.valueOf(animalId))
                .uri(uri)
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200){
                String jsonBody = response.body();
                Gson gson = new Gson();
                return gson.fromJson(jsonBody, AnimalData.class);
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
    public void updateAnimalData(Token token, AnimalData animalData) throws ConnectionError {
        URI uri = URI.create(baseUrl+"shelters/animals/update");
        Gson gson = new Gson();
        String body = gson.toJson(animalData);
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authentication", "Bearer "+token.accessToken)
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
    private String convertImageFileToBase64(File imageFile){
        // TODO
        return "";
    }
    public void updateAnimalPicture(Token token, int animalId, File imageFile) throws ConnectionError {
        URI uri = URI.create(baseUrl+"shelters/animals/update/picture");
        Gson gson = new Gson();
        String body = gson.toJson(convertImageFileToBase64(imageFile));
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authentication", "Bearer "+token.accessToken)
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
    private AnimalData[] getAnimals(Token token, String animal) throws ConnectionError {
        URI uri = URI.create(baseUrl+"shelters/animals/"+animal);
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .setHeader("Content-Type", "application/json")
                .setHeader("Authentication", "Bearer "+token.accessToken)
                .uri(uri)
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200){
                String jsonBody = response.body();
                Gson gson = new Gson();
                return gson.fromJson(jsonBody, AnimalData[].class);
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
    public AnimalData[] getCats(Token token) throws ConnectionError {
        return getAnimals(token,"cats");
    }
    public AnimalData[] getDogs(Token token) throws ConnectionError {
        return getAnimals(token,"dogs");
    }
    private void addAnimal(Token token, AnimalData animalData, File imageFile,String animal) throws ConnectionError {
        URI uri = URI.create(baseUrl+"shelters/animals/"+animal);
        Gson gson = new Gson();
        String body = gson.toJson(new AnimalDataWithImage(animalData,convertImageFileToBase64(imageFile)));
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authentication", "Bearer "+token.accessToken)
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
    public void addCat(Token token, AnimalData animalData, File imageFile) throws ConnectionError {
        addAnimal(token,animalData,imageFile,"cats");
    }
    public void addDog(Token token, AnimalData animalData, File imageFile) throws ConnectionError {
        addAnimal(token,animalData,imageFile,"dogs");
    }
    public void updatePassword(Token token, String password) throws ConnectionError {
        URI uri = URI.create(baseUrl+"shelters/update/password");
        Gson gson = new Gson();
        String body = gson.toJson(password);
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authentication", "Bearer "+token.accessToken)
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
    public void updateCapacity(Token token, int capacity) throws ConnectionError {
        URI uri = URI.create(baseUrl+"shelters/update/capacity");
        Gson gson = new Gson();
        String body = gson.toJson(capacity);
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authentication", "Bearer "+token.accessToken)
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
    public void updateName(Token token, String name) throws ConnectionError {
        URI uri = URI.create(baseUrl+"shelters/update/name");
        Gson gson = new Gson();
        String body = gson.toJson(name);
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authentication", "Bearer "+token.accessToken)
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
    public AdoptionRequestData[] getAdoptionRequests(Token token) throws ConnectionError {
        URI uri = URI.create(baseUrl+"shelters/adoption-requests");
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .setHeader("Content-Type", "application/json")
                .setHeader("Authentication", "Bearer "+token.accessToken)
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
    public void approveAdoption(Token token, int adoptionRequestId) throws ConnectionError {
        URI uri = URI.create(baseUrl+"shelters/adoption-requests/approve");
        Gson gson = new Gson();
        String body = gson.toJson(adoptionRequestId);
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authentication", "Bearer "+token.accessToken)
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
    public TaskData[] getTasks(Token token) throws ConnectionError {
        URI uri = URI.create(baseUrl+"shelters/daily-tasks");
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .setHeader("Content-Type", "application/json")
                .setHeader("Authentication", "Bearer "+token.accessToken)
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
    public void createTask(Token token, TaskData task) throws ConnectionError {
        URI uri = URI.create(baseUrl+"shelters/daily-tasks");
        Gson gson = new Gson();
        String body = gson.toJson(task);
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authentication", "Bearer "+token.accessToken)
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
    public void approveTask(Token token, int taskId) throws ConnectionError {
        URI uri = URI.create(baseUrl+"shelters/daily-tasks/approve");
        Gson gson = new Gson();
        String body = gson.toJson(taskId);
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .setHeader("Content-Type", "application/json")
                .setHeader("Authentication", "Bearer "+token.accessToken)
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